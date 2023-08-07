package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.POJO.PaymentPortalRequest;
import com.inn.nextDoorIt.entity.OrderDetails;
import com.inn.nextDoorIt.rest.OrderRest;
import com.inn.nextDoorIt.service.OrderService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestImpl implements OrderRest {
    @Autowired
    private OrderService service;

    @Override
    public ResponseEntity<Object> placeOrder(OrderDetails orderDetails) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ApplicationResponse(service.placeOrder(orderDetails), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> getOrderInfo(int userId) {
        return ResponseEntity.ok(new ApplicationResponse(service.getOrderInformation(userId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> completePayment(PaymentPortalRequest paymentRequest) {
        return ResponseEntity.ok(new ApplicationResponse(service.completePaymentRequest(paymentRequest), HttpStatus.OK.value()));
    }
}
