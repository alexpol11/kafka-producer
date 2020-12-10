package com.example.kafkaproducer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerKafkaConfig {

//    @Value("${spring.kafka.bootstrap-servers}")
    String kafkaServer = "localhost:9092";

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props;

        props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Long, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<Long, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
