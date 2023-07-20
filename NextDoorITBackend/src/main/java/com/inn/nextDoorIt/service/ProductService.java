package com.inn.nextDoorIt.service;


import com.inn.nextDoorIt.POJO.Product;
import com.inn.nextDoorIt.POJO.ProductCategory;
import com.inn.nextDoorIt.POJO.ProductRequest;

public interface ProductService {
    ProductCategory saveProductCategory(ProductCategory category);
    Product saveProduct(ProductRequest productRequest);
}
