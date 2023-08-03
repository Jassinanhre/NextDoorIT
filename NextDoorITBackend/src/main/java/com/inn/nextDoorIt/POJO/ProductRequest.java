package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private int categoryId;
    private String imageId;
    private String features;
    private String specifications;
    private long price;
}
