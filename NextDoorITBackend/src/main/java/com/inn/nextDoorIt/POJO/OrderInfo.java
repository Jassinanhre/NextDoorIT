package com.inn.nextDoorIt.POJO;

import com.inn.nextDoorIt.entity.OrderDetails;
import lombok.Data;

@Data
public class OrderInfo {
    private OrderDetails info;
    private int quantity;
    private long total;
}
