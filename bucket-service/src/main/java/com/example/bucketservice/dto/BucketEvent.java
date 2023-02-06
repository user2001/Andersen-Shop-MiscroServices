package com.example.bucketservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BucketEvent {
    private BucketStatus bucketStatus;
    private long productId;
    private long userId;

    @Override
    public String toString() {
        return "BucketEvent{" +
                "bucketStatus=" + bucketStatus.toString() +
                ", productId=" + productId +
                ", userId=" + userId +
                '}';
    }
}
