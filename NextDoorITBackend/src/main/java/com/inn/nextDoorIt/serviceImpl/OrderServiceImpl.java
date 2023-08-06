package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.OrderInfo;
import com.inn.nextDoorIt.dao.CartDao;
import com.inn.nextDoorIt.dao.CartQuantityDao;
import com.inn.nextDoorIt.dao.OrdersDao;
import com.inn.nextDoorIt.dao.UserDao;
import com.inn.nextDoorIt.entity.*;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartQuantityDao cartQuantityDao;

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

    @Override
    public OrderInfo getOrderInformation(int userId) {
        Cart userCart = cartDao.findByUserId(userId);
        List<Product> cartProducts = userCart.getProducts();
        int totalQuantity = 0;
        long orderTotal = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            CartQuantity currentCartQuantity = cartQuantityDao.findByUserIdAndProductId(userId, cartProducts.get(0).getId());
            totalQuantity += currentCartQuantity.getQuantity();
            orderTotal += cartProducts.get(i).getPrice() * currentCartQuantity.getQuantity();
        }
        OrderDetails orderDetails = orderDao.findByUserId(userId);
        OrderInfo response = new OrderInfo();
        response.setInfo(orderDetails);
        response.setQuantity(totalQuantity);
        response.setTotal(orderTotal);
        return response;
    }

    private void validateOrderRequest(OrderDetails request) {
        if (Objects.isNull(request) || request.getAddress().isBlank() || request.getName().isBlank() || request.getContactInfo().isBlank() || Objects.isNull(request.getUserId()))
            throw new ApplicationException("Invalid Order request, please specify all fields", HttpStatus.BAD_REQUEST);
    }
}
