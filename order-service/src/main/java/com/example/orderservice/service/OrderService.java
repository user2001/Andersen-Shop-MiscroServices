package com.example.orderservice.service;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.mapper.OrderItemMapper;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItem> orderItems = orderRequest.getOrderItemDtoList()
                .stream()
                .map(orderItemMapper::toEntity)
                .toList();
        order.setOrderedItems(orderItems);
        orderRepository.save(order);
    }

    public void addOrderItem(Long orderId, OrderItemDto itemDto) {
        Order order = orderRepository.findById(orderId).get();
        OrderItem orderItem = orderItemMapper.toEntity(itemDto);
        order.getOrderedItems().add(orderItem);
        orderRepository.save(order);
    }

    public void deleteOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId).get();
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();
        order.getOrderedItems().remove(orderItem);
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
