package com.inn.nextDoorIt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    private long price;
    @Column(name = "image_id")
    private String imageId;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Cart> cartList;

}
