package com.inn.nextDoorIt.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "product_review_and_ratings")
@DynamicUpdate
@DynamicInsert
@Data
public class ProductReviewAndRating {
    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id")
    private int productId;
    private String username;
    private float rating;
    private String summery;
}
