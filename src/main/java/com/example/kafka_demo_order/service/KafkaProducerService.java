package com.example.kafka_demo_order.service;


import com.example.kafka_demo_order.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String TOPIC = "order.events";

    public KafkaProducerService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Kafka Event Sent: " + event);
    }
}