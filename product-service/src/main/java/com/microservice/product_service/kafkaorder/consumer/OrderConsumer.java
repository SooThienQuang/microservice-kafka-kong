
package com.microservice.product_service.kafkaorder.consumer;
import com.microservice.product_service.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderConsumer {
    @KafkaListener(topics = "order-topic", groupId = "order-group-product")
    public void consume(Map<String, Object> message) {
        String orderId = (String) message.get("orderId");
        System.out.println("📦Product service listener kafka: "+orderId);
    }
}