package com.inn.nextDoorIt.POJO;

import com.inn.nextDoorIt.entity.ProductCategory;
import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsModel {

    private int id;


    private String productName;

    private String productDescription;

    private ProductCategory productCategory;

    private String features;
    private String specifications;
    private Float overallRating;
    private List<ProductReviewAndRating> productReviewsAndRatings;

}
