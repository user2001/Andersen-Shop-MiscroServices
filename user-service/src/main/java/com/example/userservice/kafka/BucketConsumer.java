package com.example.userservice.kafka;

import com.example.bucketservice.dto.BucketEvent;
import com.example.bucketservice.dto.BucketStatus;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BucketConsumer {
    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BucketEvent event) {

        BucketStatus bucketStatus = event.getBucketStatus();

        long userId = event.getUserId();
        long productId = event.getProductId();

        switch (bucketStatus) {
            case ADD ->
                    LOGGER.info("Product with id:" + productId + " was added to the bucket by user:" + userService.getById(userId));
            case REMOVE ->
                    LOGGER.info("Product with id:" + productId + " was removed from the bucket by user:" + userService.getById(userId));
            case SUBMIT -> LOGGER.info("User: " + userService.getById(userId) + " confirm the order");
        }
    }
}
