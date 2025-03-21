package com.example.kafka_demo_order.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private int orderId;
    private String status;
    private int qty;
}