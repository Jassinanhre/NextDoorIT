package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.OrderInfo;
import com.inn.nextDoorIt.POJO.PaymentPortalRequest;
import com.inn.nextDoorIt.POJO.PaymentResponse;
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
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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
        request.setName(user.getEmail());
        request.setOrderStatus("IN_PROCESSING"); // FOR FIRST TIME MARK THE ORDER STATUS IN PROCESSING
        // HERE WE CAN CHECK IS THERE ANY PRODUCT ACTUALLY PRESENT IN USER'S CART OR NOT
        Cart cart = cartDao.findByUserId(request.getUserId());
        if (Objects.isNull(cart.getProducts()) || cart.getProducts().size() == 0) {
            throw new ApplicationException("No product in cart, invalid place order request [EMPTY CART]", HttpStatus.CONFLICT);
        }

        OrderDetails details = orderDao.findByUserIdAndOrderStatus(request.getUserId(), "IN_PROCESSING");
        if (!Objects.isNull(details)) { // IF THE REQUEST IS FOR UPDATING ALREADY UNDER PROCESSING ORDER
            details.setAddress(request.getAddress());
            details.setName(request.getName());
            details.setContactInfo(request.getContactInfo());
            OrderDetails savedResponse = orderDao.save(details);
            if (Objects.isNull(savedResponse))
                throw new ApplicationException("Not able to save order information", HttpStatus.INTERNAL_SERVER_ERROR);
            return savedResponse;
        } else {      // IF THERE IS NO ORDER DETAILS RECORD ALREADY EXISTING IN DATABASE
            OrderDetails newSavedRecord = orderDao.save(request);
            if (Objects.isNull(newSavedRecord))
                throw new ApplicationException("Not able to save order information", HttpStatus.INTERNAL_SERVER_ERROR);
            return newSavedRecord;
        }
    }

    @Override
    public OrderInfo getOrderInformation(int userId) {
        Cart userCart = cartDao.findByUserId(userId);
        if (Objects.isNull(userCart))
            throw new ApplicationException("No cart record found for requested userId", HttpStatus.BAD_REQUEST);
        List<Product> cartProducts = userCart.getProducts();
        User user = userDao.findById(userId).orElseThrow(() -> new ApplicationException("No user found for requested userId", HttpStatus.BAD_REQUEST));
        int totalQuantity = 0;
        long orderTotal = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            CartQuantity currentCartQuantity = cartQuantityDao.findByUserIdAndProductId(userId, cartProducts.get(0).getId());
            totalQuantity += currentCartQuantity.getQuantity();
            orderTotal += cartProducts.get(i).getPrice() * currentCartQuantity.getQuantity();
        }
        OrderDetails orderDetails = orderDao.findByUserIdAndOrderStatus(userId, "IN_PROCESSING");
        if (Objects.isNull(orderDetails)) { // if no order details are found
            OrderInfo emptyInfo = new OrderInfo();
            emptyInfo.setTotal(0);
            emptyInfo.setQuantity(0);
            emptyInfo.setInfo(null);
            return emptyInfo;
        }
        orderDetails.setName(user.getName());
        OrderInfo response = new OrderInfo();
        response.setInfo(orderDetails);
        response.setQuantity(totalQuantity);
        response.setTotal(orderTotal);
        return response;
    }

    @Override
    public PaymentResponse completePaymentRequest(PaymentPortalRequest paymentRequest) {
        validatePaymentPortalRequest(paymentRequest);
        Cart cart = cartDao.findByUserId(paymentRequest.getUserId());
        // REMOVE ALL THE PRODUCTS FROM THE CART
        List<Product> productsInCart = cart.getProducts();
        if (Objects.isNull(productsInCart) || productsInCart.size() == 0) {
            throw new ApplicationException("No product is available in cart, payment failed", HttpStatus.BAD_REQUEST);
        }
        List<Product> emptyList = new ArrayList<>();
        // DELETING THE CART QUANTITY RECORDS ALSO
        for (int i = 0; i < productsInCart.size(); i++) {
            cartQuantityDao.deleteByUserIdAndProductId(paymentRequest.getUserId(), productsInCart.get(i).getId());
        }
        // REMOVING ALL THE PRODUCTS FROM CART
        cart.setProducts(emptyList);
        Cart savedCart = cartDao.save(cart);
        if (Objects.isNull(savedCart)) {
            throw new ApplicationException("Payment failure, while processing the payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // SETTING THE USER ID OF ORDER INFO AS NULL
        OrderDetails orderDetails = orderDao.findByUserIdAndOrderStatus(paymentRequest.getUserId(), "IN_PROCESSING");
        orderDetails.setOrderStatus("PAYMENT_COMPLETED");
        OrderDetails savedDetails = orderDao.save(orderDetails);
        if (Objects.isNull(savedDetails)) {
            throw new ApplicationException("Unable to save the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus("COMPLETED");
        response.setDate(new Date(System.currentTimeMillis()).toString());
        return response;
    }

    private void validatePaymentPortalRequest(PaymentPortalRequest paymentRequest) {
        if (Objects.isNull(paymentRequest) || paymentRequest.getPaymentMethod().isBlank() || paymentRequest.getCvv().isBlank() || Objects.isNull(paymentRequest.getUserId()) || paymentRequest.getExpiry().isBlank() || paymentRequest.getCardNumber().isBlank() || Objects.isNull(paymentRequest.getUserId()))
            throw new ApplicationException("Invalid payment request, fields are missing", HttpStatus.BAD_REQUEST);
    }

    private void validateOrderRequest(OrderDetails request) {
        if (Objects.isNull(request) || request.getAddress().isBlank() || request.getName().isBlank() || request.getContactInfo().isBlank() || Objects.isNull(request.getUserId()))
            throw new ApplicationException("Invalid Order request, please specify all fields", HttpStatus.BAD_REQUEST);
    }
}
