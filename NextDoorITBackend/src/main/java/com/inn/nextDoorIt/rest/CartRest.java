package com.inn.nextDoorIt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/cart")
public interface CartRest {

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProductToCart(@RequestParam("productId") int productId, @RequestParam("userId") int userId);

    @PutMapping("/removeProduct")
    public ResponseEntity<Object> removeCartProduct(@RequestParam("productId") int productId, @RequestParam("userId") int userId);

    @GetMapping("/all")
    public ResponseEntity<Object> allProductsInCart(@RequestParam("userId") int userId);
}
