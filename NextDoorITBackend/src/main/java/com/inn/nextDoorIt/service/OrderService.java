package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.OrderDetails;

public interface OrderService {
    public OrderDetails placeOrder(OrderDetails request);
}
