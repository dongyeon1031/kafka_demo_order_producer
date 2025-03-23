package com.example.kafka_demo_order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.kafka_demo_order.model.LoadTestEvent;

@Service
@RequiredArgsConstructor
public class LoadTestService {
    private final KafkaTemplate<String, LoadTestEvent> kafkaTemplate;
    private final String TOPIC = "load.test.topic";
    private final String KEY = "load-test-key";

    public void sendBulkMessages(int count) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            LoadTestEvent event = LoadTestEvent.createWithPayload("load-test-" + i, 100 * 1024);
            kafkaTemplate.send(TOPIC, KEY,  event);
        }

        kafkaTemplate.flush(); // 모든 메시지가 전송될 때까지 기다림
        long end = System.currentTimeMillis();

        System.out.println("Sent " + count + " messages in " + (end - start) + " ms");
    }
}
