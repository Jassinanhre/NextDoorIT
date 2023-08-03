package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.CartDetails;
import com.inn.nextDoorIt.dao.CartDao;
import com.inn.nextDoorIt.dao.CartQuantityDao;
import com.inn.nextDoorIt.dao.ProductDao;
import com.inn.nextDoorIt.entity.Cart;
import com.inn.nextDoorIt.entity.CartQuantity;
import com.inn.nextDoorIt.entity.Product;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartQuantityDao cartQuantityDao;

    @Override
    public List<CartQuantity> addProductToCart(int productId, int userId, int quantity) {
        Product productFromDb = productDao.findById(productId).orElseThrow(() -> new ApplicationException("No product found with requested product id", HttpStatus.BAD_REQUEST));
        // create record for cart quantity dao
        Cart cart = cartDao.findByUserId(userId);
        List<Product> productsInCart = cart.getProducts();
        // CHECKING IF THE PRODUCT IS ALREADY PRESENT IN CART THEN THROW THE EXCEPTION
        productsInCart.forEach(product -> {
            if (product.getId() == productId) { // IF PRODUCT ID MATCHES WITH REQUESTED PRODUCT ID
                throw new ApplicationException("The product is already present in cart with specific quantity", HttpStatus.BAD_REQUEST);
            }
        });
        CartQuantity cartQuantity = new CartQuantity();
        cartQuantity.setQuantity(quantity);
        cartQuantity.setUserId(userId);
        cartQuantity.setProduct(productFromDb);
        // IF PRODUCT ALREADY NOT PRESENT IN CART THEN ONLY YOU HAVE TO CREATE THIS RECORD
        if (Objects.isNull(productsInCart) || productsInCart.size() == 0) {
            List<Product> productToAdd = new ArrayList<>();
            productToAdd.add(productFromDb);
            cart.setProducts(productToAdd);
        } else {
            productsInCart.add(productFromDb);
            cart.setProducts(productsInCart);
        }
        // write code here to save the quantity of product in specific cart

        Cart savedCartResponse = cartDao.save(cart);
        if (!Objects.isNull(savedCartResponse)) {
            CartQuantity savedCartQuantity = cartQuantityDao.save(cartQuantity);
            List<CartQuantity> response = cartQuantityDao.findByUserId(userId);
            return response;
        }
        throw new ApplicationException("Error saving cart record in database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Cart removeCartProduct(int productId, int userId) {
        Product productFromDb = productDao.findById(productId).orElseThrow(() -> new ApplicationException("No product found with requested product id", HttpStatus.BAD_REQUEST));
        Cart cart = cartDao.findByUserId(userId);
        List<Product> productsInCart = cart.getProducts();

        if (Objects.isNull(productsInCart) || productsInCart.size() == 0) {
            throw new ApplicationException("No product is present for cart for requested user", HttpStatus.BAD_REQUEST);
        }
        List<Product> productsToRemove = productsInCart.stream().filter(product -> {
            return product.getId() == productId;
        }).collect(Collectors.toList());
        if (Objects.isNull(productsToRemove) || productsToRemove.size() == 0) {
            throw new ApplicationException("No product is present in cart which is requested to remove", HttpStatus.BAD_REQUEST);
        }
        int removeIndex = 0;
        // REMOVE THE PRODUCT FROM THE CART
        for (int i = 0; i < productsInCart.size(); i++) {
            if (productsInCart.get(i).getId() == productId) {
                removeIndex = i;
                break;
            }
        }
        productsInCart.remove(removeIndex);
        cart.setProducts(productsInCart);
        Cart savedCartResponse = cartDao.save(cart);
        if (!Objects.isNull(savedCartResponse)) {
            cartQuantityDao.deleteWithUserIdAndProductId(userId, productId);
            return savedCartResponse;
        }
        throw new ApplicationException("Error while saving product to cart", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Map<String, Object> getCart(int userId) {
        List<CartQuantity> cartDetails = cartQuantityDao.findByUserId(userId);
        if (Objects.isNull(cartDetails)) {
            throw new ApplicationException("No cart found for requested userId", HttpStatus.BAD_REQUEST);
        }
        List<CartDetails> cartDetailsResponses = new ArrayList<>();
        Map<String, Object> finalResponse = new HashMap<>();
        AtomicLong totalPrice = new AtomicLong();
        cartDetails.forEach(cartRecord -> {
            CartDetails temp = new CartDetails();
            temp.setProduct(cartRecord.getProduct());
            temp.setQuantity(cartRecord.getQuantity());
            cartDetailsResponses.add(temp);
            totalPrice.set(totalPrice.get() + (cartRecord.getProduct().getPrice() * cartRecord.getQuantity()));
        });
        finalResponse.put("productDetails", cartDetailsResponses);
        finalResponse.put("orderTotalPrice", totalPrice);
        return finalResponse;
    }

    // RETURNING THE MAP BECAUSE , REDIS CACHE STORES OUR OBJECT IN MAP FORM
    // SO IT WILL GIVE CLASS CAST EXCEPTION
    private Map<String, Object> buildMapResponse(Cart cart) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", cart.getId());
        response.put("userId", cart.getUserId());
        response.put("username", cart.getUsername());
        response.put("products", cart.getProducts());
        return response;
    }
}
