package com.inn.nextDoorIt.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "training_review_and_ratings")
@DynamicUpdate
@DynamicInsert
@Data
public class TrainingReviewRatings {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "training_id")
    private int trainingId;
    private String username;
    private float rating;
    private String summery;
}
