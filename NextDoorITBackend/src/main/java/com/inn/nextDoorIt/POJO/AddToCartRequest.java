package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class AddToCartRequest {
    private int userId;
    private int productId;
    private int quantity;
}
