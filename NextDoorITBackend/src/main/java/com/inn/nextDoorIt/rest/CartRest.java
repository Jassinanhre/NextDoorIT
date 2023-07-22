package com.inn.nextDoorIt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "/cart")
public interface CartRest {

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProductToCart(@RequestParam("productId") int productId, @RequestParam("userId") int userId);

    @GetMapping("/all")
    public ResponseEntity<Object> allProductsInCart(@RequestParam("userId") int userId);
}
