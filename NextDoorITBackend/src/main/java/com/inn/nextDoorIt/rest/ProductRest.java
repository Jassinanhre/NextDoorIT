package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.ProductCategory;
import com.inn.nextDoorIt.POJO.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
public interface ProductRest {

    @PostMapping("/createCategory")
    public ResponseEntity<Object> createProductCategory(@RequestBody ProductCategory productCategory);

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest);
}
