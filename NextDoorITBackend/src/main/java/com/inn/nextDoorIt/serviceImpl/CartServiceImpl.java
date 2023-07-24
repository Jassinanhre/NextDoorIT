package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.Cart;
import com.inn.nextDoorIt.POJO.Product;
import com.inn.nextDoorIt.dao.CartDao;
import com.inn.nextDoorIt.dao.ProductDao;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Cart addProductToCart(int productId, int userId) {
        Product productFromDb = productDao.findById(productId).orElseThrow(() -> new ApplicationException("No product found with requested product id", HttpStatus.BAD_REQUEST));
        Cart cart = cartDao.findByUserId(userId);

        List<Product> productsInCart = cart.getProducts();
        if (Objects.isNull(productsInCart) || productsInCart.size() == 0) {
            List<Product> productToAdd = new ArrayList<>();
            productToAdd.add(productFromDb);
            cart.setProducts(productToAdd);
        } else {
            productsInCart.add(productFromDb);
            cart.setProducts(productsInCart);
        }
        Cart savedCartResponse = cartDao.save(cart);
        if (!Objects.isNull(savedCartResponse)) {
            return savedCartResponse;
        }
        throw new ApplicationException("Error saving cart record in database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Cart getCart(int userId) {
        Cart savedCart = cartDao.findByUserId(userId);
        if (!Objects.isNull(savedCart)) {
            return savedCart;
        }
        throw new ApplicationException("No cart found with requested user id", HttpStatus.BAD_REQUEST);
    }
}
