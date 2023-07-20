package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.POJO.ProductCategory;
import com.inn.nextDoorIt.POJO.ProductRequest;
import com.inn.nextDoorIt.rest.ProductRest;
import com.inn.nextDoorIt.service.ProductService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestImpl implements ProductRest {
    @Autowired
    private ProductService service;

    @Override
    public ResponseEntity<Object> createProductCategory(ProductCategory productCategory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(service.saveProductCategory(productCategory), HttpStatus.CREATED.value()));
    }


    @Override
    public ResponseEntity<Object> createProduct(ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(service.saveProduct(productRequest), HttpStatus.CREATED.value()));
    }
}
