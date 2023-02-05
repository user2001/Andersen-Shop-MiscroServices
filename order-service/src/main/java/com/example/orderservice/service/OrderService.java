package com.example.orderservice.service;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.mapper.OrderItemMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItem> orderItems = orderRequest.getOrderItemDtoList()
                .stream()
                .map(orderItemMapper::toEntity)
                .toList();
        order.setOrderedItems(orderItems);
        orderRepository.save(order);
    }

    public void addOrderItem(Long orderId,OrderItemDto itemDto){
        Order order = orderRepository.findById(orderId).get();
        OrderItem orderItem = orderItemMapper.toEntity(itemDto);
        order.getOrderedItems().add(orderItem);
        orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        return orderRepository.findById(id).get();
    }

}
