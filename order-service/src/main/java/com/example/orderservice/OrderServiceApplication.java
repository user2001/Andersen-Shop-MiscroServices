package com.example.orderservice;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
//        return args -> {
//            OrderItem orderItem=new OrderItem();
//            orderItem.setPrice(BigDecimal.valueOf(300));
//            orderItem.setSkuCode("Book");
//            orderItem.setQuantity(1);
//
//            OrderItem orderItem2=new OrderItem();
//            orderItem.setPrice(BigDecimal.valueOf(1200));
//            orderItem.setSkuCode("Laptop");
//            orderItem.setQuantity(1);
//
//            orderItemRepository.save(orderItem);
//            orderItemRepository.save(orderItem2);
//
//            Order order=new Order();
//            order.setOrderedItems(List.of(orderItem,orderItem2));
//            order.setOrderNumber(UUID.randomUUID().toString());
//
//            Order order2=new Order();
//            order.setOrderedItems(List.of(orderItem));
//            order.setOrderNumber(UUID.randomUUID().toString());
//
//            orderRepository.save(order);
//            orderRepository.save(order2);
//        };
//    }
}
