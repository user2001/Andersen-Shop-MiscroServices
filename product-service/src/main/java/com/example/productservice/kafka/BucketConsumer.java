package com.example.productservice.kafka;

import com.example.bucketservice.dto.BucketEvent;
import com.example.bucketservice.dto.BucketStatus;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BucketConsumer {

    private final ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BucketEvent event) {

        BucketStatus bucketStatus = event.getBucketStatus();

        long userId = event.getUserId();
        long productId = event.getProductId();

        switch (bucketStatus) {
            case ADD ->
                    LOGGER.info("User with id:" + userId + " add product: " + productService.getProductById(productId) + " to the bucket");
            case REMOVE ->
                    LOGGER.info("User with id:" + userId + " remove product: " + productService.getProductById(productId) + " from the bucket");
            case SUBMIT -> LOGGER.info("User with id:" + userId + " confirm the order");
        }
    }
}
