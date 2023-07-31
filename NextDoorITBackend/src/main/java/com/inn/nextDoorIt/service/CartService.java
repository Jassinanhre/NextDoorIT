package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.Cart;

import java.util.Map;

public interface CartService {
    public Cart addProductToCart(int productId, int userId);

    public Cart removeCartProduct(int productId, int cartId);

    public Map<String, Object> getCart(int userId);
}
