package com.mmc.playground.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerClient {

    private final KafkaConsumer<String, Long> consumer;

    public KafkaConsumerClient(Properties properties) {
        this.consumer = new KafkaConsumer<>(properties);
    }

    public void listen() throws IOException {
        ConsumerRecords<String, Long> records = consumer.poll(Duration.ofSeconds(5));
        for (ConsumerRecord<String, Long> record : records) {
            System.out.println("Event: key: " + record.key() + " value: " + record.value());
        }
    }

    void registerTopic(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
    }

}
