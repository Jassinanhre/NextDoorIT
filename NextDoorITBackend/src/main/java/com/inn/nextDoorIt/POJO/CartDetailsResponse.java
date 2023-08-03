package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class CartDetailsResponse {
    private CartDetails cartDetails;
    private long totalPrice;
}
