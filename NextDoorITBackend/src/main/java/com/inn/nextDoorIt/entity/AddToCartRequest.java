package com.inn.nextDoorIt.entity;

import lombok.Data;

@Data
public class AddToCartRequest {
    private int userId;
    private int productId;
    private int quantity;
}
