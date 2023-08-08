package com.inn.nextDoorIt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String name;

    private String address;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "order_status")
    private String orderStatus;
}
