package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.AddToCartRequest;
import com.inn.nextDoorIt.entity.Cart;
import com.inn.nextDoorIt.entity.CartQuantity;

import java.util.List;
import java.util.Map;

public interface CartService {
    public List<CartQuantity> addProductToCart(AddToCartRequest addToCartRequest);

    public List<CartQuantity> removeCartProduct(int productId, int cartId);

    public Map<String, Object> getCart(int userId);
}
