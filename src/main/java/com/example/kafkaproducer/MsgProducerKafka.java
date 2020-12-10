package com.example.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgProducerKafka {
    @Value("${kafka.topic}")
    String kafkaTopic;

    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping
    public void sendMsg(Long msgId, String msg) {
        kafkaTemplate.send(kafkaTopic, msgId, msg);
    }
}
