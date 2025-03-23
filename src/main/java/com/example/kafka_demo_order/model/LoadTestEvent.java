package com.example.kafka_demo_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadTestEvent {
    private String id;
    private String content;

    public static LoadTestEvent createWithPayload(String id, int sizeInBytes) {
        return new LoadTestEvent(id, "B".repeat(Math.max(0, sizeInBytes)));
    }
}
