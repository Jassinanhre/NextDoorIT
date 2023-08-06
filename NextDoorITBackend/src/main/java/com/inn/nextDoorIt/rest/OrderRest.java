package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.entity.OrderDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
public interface OrderRest {
    @PostMapping("/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDetails orderDetails);
}
