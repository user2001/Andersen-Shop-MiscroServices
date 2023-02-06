package com.example.orderservice.kafka;

import com.example.bucketservice.dto.BucketEvent;
import com.example.bucketservice.dto.BucketStatus;
import com.example.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BucketConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BucketConsumer.class);
    private final OrderService orderService;
    private final Map<Long, List<Long>> orders = new HashMap<>();

    public BucketConsumer(OrderService orderService) {
        this.orderService = orderService;
    }


    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BucketEvent event) {

        LOGGER.info(String.format("BucketEvent received in a order service=> %s", event.toString()));

        BucketStatus bucketStatus = event.getBucketStatus();

        long userId = event.getUserId();
        long productId = event.getProductId();
        List<Long> idOfProducts = orders.get(userId);

        switch (bucketStatus) {
            case ADD:
                if (!orders.containsKey(userId)) {
                    List<Long> productsId = new ArrayList<>();
                    productsId.add(productId);
                    orders.put(userId, productsId);
                } else {
                    idOfProducts.add(productId);
                    orders.put(userId, idOfProducts);
                }
                break;
            case REMOVE:
                idOfProducts.remove(productId);
                orders.put(userId, idOfProducts);
                break;

            case SUBMIT:
                orderService.placeOrder(userId, idOfProducts);//save order to db
                break;
        }

    }
}
