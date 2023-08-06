package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.dao.OrdersDao;
import com.inn.nextDoorIt.dao.UserDao;
import com.inn.nextDoorIt.entity.OrderDetails;
import com.inn.nextDoorIt.entity.User;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersDao orderDao;

    @Autowired
    private UserDao userDao;

    @Override
    public OrderDetails placeOrder(OrderDetails request) {
        validateOrderRequest(request);
        User user = userDao.findById(request.getUserId()).orElseThrow(() -> new ApplicationException("No user found for requested userId", HttpStatus.BAD_REQUEST));
        var savedOrderResponse = orderDao.save(request);
        if (Objects.isNull(savedOrderResponse)) {
            throw new ApplicationException("Error while saving order request ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return savedOrderResponse;
    }

    private void validateOrderRequest(OrderDetails request) {
        if (Objects.isNull(request) || request.getAddress().isBlank() || request.getName().isBlank() || request.getContactInfo().isBlank() || Objects.isNull(request.getUserId()))
            throw new ApplicationException("Invalid Order request, please specify all fields", HttpStatus.BAD_REQUEST);
    }
}
