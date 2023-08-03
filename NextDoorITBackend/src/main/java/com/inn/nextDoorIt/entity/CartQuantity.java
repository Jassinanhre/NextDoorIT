package com.inn.nextDoorIt.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "cart_quantity")
// THIS TABLE WILL CONTAIN THE INFORMATION OF CART PRODUCT AND ITS QUANTITY
public class CartQuantity {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

    private int quantity;
}
