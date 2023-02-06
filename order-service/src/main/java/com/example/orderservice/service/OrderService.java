package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOwnerId(order.getOwnerId());
        order.setProductsId(orderRequest.getProductId());
        orderRepository.save(order);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }

    public OrderResponse getById(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return orderMapper.toDto(order);
    }

    public void deleteOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        orderRepository.delete(order);
    }

}
