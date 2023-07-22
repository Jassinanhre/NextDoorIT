package com.inn.nextDoorIt.POJO;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;
    private String username;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

}
