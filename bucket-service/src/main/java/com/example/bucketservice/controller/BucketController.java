package com.example.bucketservice.controller;

import com.example.bucketservice.dto.BucketDto;
import com.example.bucketservice.dto.BucketEvent;
import com.example.bucketservice.dto.BucketStatus;
import com.example.bucketservice.kafka.BucketProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bucket")
public class BucketController {
    private final BucketProducer bucketProducer;

    @PostMapping("/add")
    public void addProduct(@RequestBody BucketDto bucketDto) {
        BucketEvent bucketEvent = new BucketEvent();
        bucketEvent.setProductId(bucketDto.getProductId());
        bucketEvent.setUserId(bucketDto.getUserId());
        bucketEvent.setBucketStatus(BucketStatus.ADD);

        bucketProducer.sendMessage(bucketEvent);
    }
    @PostMapping("/remove")
    public void removeProduct(@RequestBody BucketDto bucketDto) {
        BucketEvent bucketEvent = new BucketEvent();
        bucketEvent.setProductId(bucketDto.getProductId());
        bucketEvent.setUserId(bucketDto.getUserId());
        bucketEvent.setBucketStatus(BucketStatus.REMOVE);

        bucketProducer.sendMessage(bucketEvent);
    }
    @PostMapping("/submit")
    public void submit(@RequestBody BucketDto bucketDto) {
        BucketEvent bucketEvent = new BucketEvent();
        bucketEvent.setProductId(bucketDto.getProductId());
        bucketEvent.setUserId(bucketDto.getUserId());
        bucketEvent.setBucketStatus(BucketStatus.SUBMIT);

        bucketProducer.sendMessage(bucketEvent);
    }
}
