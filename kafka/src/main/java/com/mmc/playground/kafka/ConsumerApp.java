package com.mmc.playground.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.io.IOException;
import java.util.Properties;

public class ConsumerApp {

    public static void main(String[] args) throws IOException {

        String groupId = "com.mmc.playground.consumer_group_1";
        String isolation = "read_committed";
        String autoCommit = "false";
        String topic = "topic2";
        String consumerId = groupId + Math.random();

        Properties props = new Properties();
        // required props
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // required props
        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, consumerId);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // earliest, latest
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, isolation);

        KafkaConsumerClient consumer = new KafkaConsumerClient(props);
        consumer.registerTopic(topic);

        while (true) {
            consumer.listen();
        }
    }
}
