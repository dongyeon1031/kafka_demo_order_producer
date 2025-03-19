package com.example.kafka_demo.controll;

import com.example.kafka_demo.model.OrderEvent;
import com.example.kafka_demo.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent event) {
        kafkaProducerService.sendOrderEvent(event);
        return ResponseEntity.ok("Order created and event published to Kafka.");
    }
}