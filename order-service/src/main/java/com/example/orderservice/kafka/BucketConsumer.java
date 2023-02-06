package com.example.orderservice.kafka;

import com.example.bucketservice.dto.BucketEvent;
import com.example.bucketservice.dto.BucketStatus;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BucketConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BucketConsumer.class);
    private final OrderService orderService;
    private Map<Long, List<Long>> orders = new HashMap<>();

    public BucketConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BucketEvent event) {

        LOGGER.info(String.format("BucketEvent received in a order service=> %s", event.toString()));

        BucketStatus bucketStatus = event.getBucketStatus();

        switch (bucketStatus) {
            case ADD -> orders.put(event.getUserId(),
                    List.of(event.getProductId()));
            case REMOVE -> {
                List<Long> productsId = orders.get(event.getUserId());
                productsId.remove(event.getProductId());
                orders.replace(event.getUserId(), productsId);
            }
            case SUBMIT -> {
                OrderRequest orderRequest = new OrderRequest(
                        event.getUserId(), orders.get(event.getUserId()));
                orderService.placeOrder(orderRequest);
            }
        }

    }
}
