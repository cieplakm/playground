package com.mmc.playground.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerApp {

    public static void main(String[] args) {
        Properties props = new Properties();

        double random = Math.random();
        //required props
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //optional props
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "Producer_" + random);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tid" + random);

        ProducerRecord<String, String> record = new ProducerRecord<>("topic", "elo_key", "elo");

        KafkaProducerClient producerApp = new KafkaProducerClient(props);
        producerApp.produce(record);
    }
}
