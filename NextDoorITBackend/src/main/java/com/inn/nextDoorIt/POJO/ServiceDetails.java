package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class ServiceDetails {
    private String serviceName;
    private String description;
    private long price;
    private long duration;
}
