package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.CartDetails;
import com.inn.nextDoorIt.dao.CartDao;
import com.inn.nextDoorIt.dao.CartQuantityDao;
import com.inn.nextDoorIt.dao.ProductDao;
import com.inn.nextDoorIt.entity.AddToCartRequest;
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
    public List<CartQuantity> addProductToCart(AddToCartRequest addToCartRequest) {
        Product productFromDb = productDao.findById(addToCartRequest.getProductId()).orElseThrow(() -> new ApplicationException("No product found with requested product id", HttpStatus.BAD_REQUEST));
        Cart cart = cartDao.findByUserId(addToCartRequest.getUserId());
        List<Product> productsInCart = cart.getProducts();
        List<Product> alreadyPresent = productsInCart.stream().filter(product -> product.getId() == addToCartRequest.getProductId()).collect(Collectors.toList());
        if (!Objects.isNull(alreadyPresent) && alreadyPresent.size() > 0) {
            CartQuantity cartQuantityFromDb = cartQuantityDao.findByUserIdAndProductId(addToCartRequest.getUserId(), addToCartRequest.getProductId());
            cartQuantityFromDb.setQuantity(addToCartRequest.getQuantity());
            CartQuantity savedCartQuantity = cartQuantityDao.save(cartQuantityFromDb);
            if (Objects.isNull(savedCartQuantity)) {
                throw new ApplicationException("Error while changing product quantity", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            List<CartQuantity> response = cartQuantityDao.findByUserId(addToCartRequest.getUserId());
            if (Objects.isNull(response) || response.size() == 0) {
                throw new ApplicationException("No record found in response", HttpStatus.NO_CONTENT);
            }
            return response;
        } else { // IF PRODUCT IS NOT PRESENT IN CART THEN WE NEED TO ADD THAT PRODUCT TO CART
            // THIS IS THE FIRST TIME PROCESS
            CartQuantity cartQuantity = new CartQuantity();
            cartQuantity.setQuantity(addToCartRequest.getQuantity());
            cartQuantity.setUserId(addToCartRequest.getUserId());
            cartQuantity.setProduct(productFromDb);

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
                CartQuantity savedCartQuantity = cartQuantityDao.save(cartQuantity);
                List<CartQuantity> response = cartQuantityDao.findByUserId(addToCartRequest.getUserId());
                return response;
            }
        }
        throw new ApplicationException("Error saving cart record in database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<CartQuantity> removeCartProduct(int productId, int userId) {
        Product productFromDb = productDao.findById(productId).orElseThrow(() -> new ApplicationException("No product found with requested product id", HttpStatus.BAD_REQUEST));
        Cart cart = cartDao.findByUserId(userId);
        List<Product> productsInCart = cart.getProducts();
        List<Product> alreadyAvailable = productsInCart.stream().filter(product -> product.getId() == productId).collect(Collectors.toList());
        if (Objects.isNull(alreadyAvailable) || alreadyAvailable.size() == 0) {
            throw new ApplicationException("No product with requested product id is present in cart", HttpStatus.BAD_REQUEST);
        }
        // ALL PRODUCTS EXCLUDING PRODUCT TO REMOVE
        List<Product> updatedListOfProducts = productsInCart.stream().filter(product -> product.getId() != productId).collect(Collectors.toList());
        cart.setProducts(updatedListOfProducts);
        Cart savedCartResponse = cartDao.save(cart);
        if (Objects.isNull(savedCartResponse)) {
            throw new ApplicationException("Error while saving cart record in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        cartQuantityDao.deleteWithUserIdAndProductId(userId, productId);
        List<CartQuantity> response = cartQuantityDao.findByUserId(userId);
        if (Objects.isNull(response) || response.size() == 0) {
            throw new ApplicationException("No data found", HttpStatus.NO_CONTENT);
        }
        return response;
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
