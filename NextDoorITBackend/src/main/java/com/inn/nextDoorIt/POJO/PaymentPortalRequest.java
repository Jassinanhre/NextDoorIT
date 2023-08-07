package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class PaymentPortalRequest {
    private String paymentMethod;
    private String cardNumber;
    private String expiry;
    private String cvv;

    private int userId;
}
