
package com.microservice.product_service.kafkaorder;
import com.microservice.product_service.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendOrder(Order order) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("orderId","11");
        kafkaTemplate.send("order-topic", properties);
        return "✅ Sent order: " + order.getId();
    }

    public void sendOrderId(String id) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("orderId",id);
        kafkaTemplate.send("order-topic", properties);
    }
}