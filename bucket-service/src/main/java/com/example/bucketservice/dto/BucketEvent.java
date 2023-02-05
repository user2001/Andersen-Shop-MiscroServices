package com.example.bucketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BucketEvent {
    private BucketStatus bucketStatus;
    private long productId;
    private long userId;
}
