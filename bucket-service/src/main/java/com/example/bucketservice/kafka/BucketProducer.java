package com.example.bucketservice.kafka;

import com.example.bucketservice.dto.BucketEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BucketProducer {
    private static final Logger LOGGER= LoggerFactory.getLogger(BucketProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, BucketEvent> kafkaTemplate;

    public void sendMessage(BucketEvent event){
        LOGGER.info(String.format("Order event=> %s",event.toString()));

        Message<BucketEvent> message= MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}