package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order place successfully";
    }

    @PostMapping("/{id}/add")
    public String addOrderItem(@RequestBody OrderItemDto orderItemDto,@PathVariable long id){
        orderService.addOrderItem(id,orderItemDto);
        return "Order item was successfully added";
    }
    @DeleteMapping("{orderId}/remove/{orderItemId}")
    public String deleteOrderItem(@PathVariable long orderId, @PathVariable long orderItemId){
        orderService.deleteOrderItem(orderId,orderItemId);
        return "item "+orderItemId+" deleted from order";
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id){
        return orderService.getById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String removeOrder(@PathVariable long id){
        orderService.deleteOrderById(id);
        return "Order with id:"+id+" deleted";
    }
}
