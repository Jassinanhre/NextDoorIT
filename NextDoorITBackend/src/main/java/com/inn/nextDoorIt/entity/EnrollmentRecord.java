package com.inn.nextDoorIt.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
@Data
@Table(name = "training_enrollments")
public class EnrollmentRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    private String name;
    private String email;
    @Column(name = "training_type")
    private String trainingType;
    private String objective;
    @Column(name = "user_id")
    private int userId;
}
