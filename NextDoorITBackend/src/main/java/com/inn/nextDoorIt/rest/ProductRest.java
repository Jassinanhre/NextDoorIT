package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.entity.ProductCategory;
import com.inn.nextDoorIt.POJO.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
public interface ProductRest {

    @PostMapping("/createCategory")
    public ResponseEntity<Object> createProductCategory(@RequestBody ProductCategory productCategory);

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest);

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts();

    @GetMapping("/productDetails")
    public ResponseEntity<Object> getProductDetails(@RequestParam("productId") int productId);

    @GetMapping("/category")
    public ResponseEntity<Object> getProductByCategory(@RequestParam("categoryId") int categoryId);

    @GetMapping("/allCategory")
    public ResponseEntity<Object> getAllProductCategories();
}
