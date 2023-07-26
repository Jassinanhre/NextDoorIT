package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class ITTrainingRequest {
    private String name;
    private String description;
    private int categoryId;
    private String imageId;
    private long price;
    private long duration;
}
