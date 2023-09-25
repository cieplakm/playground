package com.mmc.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.UUID;


public class KafkaConsumerBuilder<K, V> {

    private final Properties properties;

    private KafkaConsumerBuilder(String consumerGroupId) {
        properties = defaultProperties();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
    }


    public KafkaConsumerBuilder<K, V> withInstanceId(String id) {
        properties.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, id);
        return this;
    }

    public KafkaConsumerBuilder<K, V> readCommitted() {
        properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        return this;
    }

    public KafkaConsumerBuilder<K, V> readUncommitted() {
        properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_uncommitted");
        return this;
    }

    public KafkaConsumerBuilder<K, V> readFromBeginning() {
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return this;
    }

    public KafkaConsumerBuilder<K, V> readFromTheEnd() {
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return this;
    }

    public KafkaConsumerBuilder<K, V> enableAutoCommit() {
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return this;
    }

    public KafkaConsumerBuilder<K, V> disableAutoCommit() {
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return this;
    }

    public KafkaConsumerBuilder<K, V> pullMessagesAtOnce(int amount) {
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, amount);
        return this;
    }

    public KafkaConsumerBuilder<K, V> withStringKeyDeserializer() {
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return this;
    }

    public KafkaConsumerBuilder<K, V> withStringValueDeserializer() {
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return this;
    }

    public KafkaConsumerBuilder<K, V> withKeyDeserializer(String keyDeserializer) {
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        return this;
    }

    public KafkaConsumerBuilder<K, V> withValueDeserializer(String valueDeserializer) {
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        return this;
    }

    public KafkaConsumerBuilder<K, V> withServerAt(String bootstrapAddress) {
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return this;
    }

    public static <K, V> KafkaConsumerBuilder<K, V> instance(String consumerGroupId) {

        return new KafkaConsumerBuilder<>(consumerGroupId);
    }

    public KafkaConsumerBuilder<K, V> withConfig(String key, String value) {
        properties.put(key, value);
        return this;
    }

    public KafkaConsumer<K, V> build() {
        return new KafkaConsumer<>(properties);
    }

    private Properties defaultProperties() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, UUID.randomUUID().toString());
        return props;
    }
}