package com.inn.nextDoorIt.POJO;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service_requests")
@Data
public class ServiceRequestRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "training_type")
    private String trainingType;

    @Column(name = "user_query")
    private String userQuery;
}
