package com.inn.nextDoorIt.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service_reviews_and_ratings")
@DynamicUpdate
@DynamicInsert
@Data
public class ReviewAndRatingsRecord implements Serializable {
    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_id")
    private int serviceId;
    private String username;
    private float rating;


    private String summery;
}
