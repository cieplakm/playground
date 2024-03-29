package com.mmc.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerClient {

    private final Producer<String, String> producer;

    public KafkaProducerClient(Properties props) {
        this.producer = new KafkaProducer<>(props);
        producer.initTransactions();
    }

    public void produce(ProducerRecord<String, String> record) {
        producer.beginTransaction();
        producer.send(record);
        producer.commitTransaction();
    }
}