package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.OrderInfo;
import com.inn.nextDoorIt.entity.OrderDetails;

public interface OrderService {
    public OrderDetails placeOrder(OrderDetails request);

    public OrderInfo getOrderInformation(int userId);
}
