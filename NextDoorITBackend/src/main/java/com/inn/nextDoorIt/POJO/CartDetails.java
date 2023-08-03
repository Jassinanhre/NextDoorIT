package com.inn.nextDoorIt.POJO;

import com.inn.nextDoorIt.entity.Product;
import lombok.Data;

@Data
public class CartDetails {
    private Product product;
    private int quantity;
}
