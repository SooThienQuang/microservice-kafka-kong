
package com.microservice.user_service.consumer;
import com.fasterxml.jackson.databind.JsonNode;
import com.microservice.user_service.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderConsumer {
    @KafkaListener(topics = "order-topic", groupId = "order-group-user")
    public void consumeOrder(Map<String, Object> message) {
        String orderId = (String) message.get("orderId");
        System.out.println("📦 Received order: " + orderId);
    }
}