package com.microservice.product_service.controller;

import com.microservice.product_service.kafkaorder.OrderProducer;
import com.microservice.product_service.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final OrderProducer producer;

    public ProductController(OrderProducer producer) {
        this.producer = producer;
    }

    @GetMapping
    public String getProducts() {
        Order order=new Order();
        order.setId("Tesst kafka");
        producer.sendOrder(order);
        return "Success";
    }

    @GetMapping("/order")
    public String order(@RequestParam("id") String id) {
         producer.sendOrderId(id);
         return "Send orderId:"+id;
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
