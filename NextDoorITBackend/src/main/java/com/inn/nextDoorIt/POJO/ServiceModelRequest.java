package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class ServiceModelRequest {
    private String serviceName;
    private String description;
    private int categoryId;
    private String userOverallRating;
    private String imageId;
    private long price;
    private long duration;
}
