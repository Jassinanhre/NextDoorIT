package com.inn.nextDoorIt.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "it_trainings")
public class ITTraining {
    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private String name;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_category")
    private TrainingCategory trainingCategory;

    @Column(name = "image_id")
    private String imageId;


    private long price;

    private long duration;

    private String objective;

    private String syllabus;
    private String prerequisites;
}
