package com.ashishontech.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(value = "kafka.enabled", havingValue = "true")
public final class ConsumerService {

    @KafkaListener(topics = "kafkaTopic", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message, Acknowledgment ack) {
        log.info("Consumed message: {}}", message);
        if("test".equals(message)){
            throw new IllegalArgumentException("Error");
        }
         ack.acknowledge();
    }
}
