package com.inn.nextDoorIt.POJO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ServiceDetailsModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String serviceName;
    private String description;
    private Category category;
    private Float userOverallRating;
    private String imageId;
    private long price;
    private long duration;
    private List<ReviewAndRatingsRecord> reviewRatings;
}
