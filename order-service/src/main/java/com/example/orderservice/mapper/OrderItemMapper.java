package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
OrderItemDto toDto(OrderItem orderItem);
OrderItem toEntity(OrderItemDto orderItemDto);
}
