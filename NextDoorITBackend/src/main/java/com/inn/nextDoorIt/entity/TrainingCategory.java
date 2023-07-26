package com.inn.nextDoorIt.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "training_category")
public class TrainingCategory {

    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_description")
    private String description;

}
