package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.rest.CartRest;
import com.inn.nextDoorIt.service.CartService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestImpl implements CartRest {
    @Autowired
    private CartService service;

    @Override
    public ResponseEntity<Object> addProductToCart(int productId, int userId) {
        return ResponseEntity.ok(new ApplicationResponse(service.addProductToCart(productId, userId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> removeCartProduct(int productId, int userId) {
        return ResponseEntity.ok(new ApplicationResponse(service.removeCartProduct(productId, userId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> allProductsInCart(int userId) {
        return ResponseEntity.ok(new ApplicationResponse(service.getCart(userId), HttpStatus.OK.value()));
    }
}
