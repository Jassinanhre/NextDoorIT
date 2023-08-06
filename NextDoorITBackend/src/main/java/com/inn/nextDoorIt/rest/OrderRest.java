package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.entity.OrderDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderRest {
    @PostMapping("/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDetails orderDetails);

    @GetMapping("/info")
    public ResponseEntity<Object> getOrderInfo(@RequestParam("userId") int userId);
}
