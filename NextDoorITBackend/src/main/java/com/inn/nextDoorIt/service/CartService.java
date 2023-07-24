package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.Cart;
import com.inn.nextDoorIt.POJO.Product;

public interface CartService {
    public Cart addProductToCart(int productId, int userId);

    public Cart getCart(int userId);
}
