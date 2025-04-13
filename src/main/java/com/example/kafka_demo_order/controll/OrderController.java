package com.example.kafka_demo_order.controll;

import com.example.kafka_demo_order.OrderEvent;
import com.example.kafka_demo_order.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.kafka_demo_order.model.OrderEventRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEventRequest request) {
        // API 요청 DTO → Avro 객체로 변환
        OrderEvent event = OrderEvent.newBuilder()
                .setOrderId(request.getOrderId())
                .setStatus(request.getStatus())
                .setQty(request.getQty())
                .build();

        kafkaProducerService.sendOrderEvent(event);
        return ResponseEntity.ok("Order created and event published to Kafka.");
    }
}