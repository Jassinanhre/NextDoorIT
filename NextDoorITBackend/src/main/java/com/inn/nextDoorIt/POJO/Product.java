package com.inn.nextDoorIt.POJO;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    private String features;
    private String specifications;


}
