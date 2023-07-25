package com.inn.nextDoorIt.service;


import com.inn.nextDoorIt.POJO.Product;
import com.inn.nextDoorIt.POJO.ProductCategory;
import com.inn.nextDoorIt.POJO.ProductDetailsModel;
import com.inn.nextDoorIt.POJO.ProductRequest;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductCategory saveProductCategory(ProductCategory category);

    Product saveProduct(ProductRequest productRequest);

    List<Product> allProducts();

    Map<String,Object> getProductDetails(int productId);

    List<Product> getProductFromCategory(int categoryId);
}
