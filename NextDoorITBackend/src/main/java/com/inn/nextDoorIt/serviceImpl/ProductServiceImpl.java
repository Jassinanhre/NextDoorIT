package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.Product;
import com.inn.nextDoorIt.POJO.ProductCategory;
import com.inn.nextDoorIt.POJO.ProductRequest;
import com.inn.nextDoorIt.dao.ProductCategoryDao;
import com.inn.nextDoorIt.dao.ProductDao;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductCategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductCategory saveProductCategory(ProductCategory category) {
        validateProductCategoryRequest(category);
        ProductCategory savedProductCategory = categoryDao.save(category);
        if (!Objects.isNull(savedProductCategory)) {
            return savedProductCategory;
        }
        throw new ApplicationException("Unable to save product category", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Product saveProduct(ProductRequest productRequest) {
        validateProductRequest(productRequest);
        ProductCategory category = categoryDao.findById(productRequest.getCategoryId()).orElseThrow(() -> new ApplicationException("No product category found for requested category id", HttpStatus.BAD_REQUEST));
        Product product = generateProductObject(productRequest, category);
        Product savedProduct = productDao.save(product);
        if (!Objects.isNull(savedProduct)) {
            return savedProduct;
        }
        throw new ApplicationException("Product not saved/created", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Product generateProductObject(ProductRequest request, ProductCategory category) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setProductDescription(request.getProductDescription());
        product.setProductCategory(category);
        product.setFeatures(request.getFeatures());
        product.setSpecifications(request.getSpecifications());
        product.setCustomerReviews(request.getCustomerReviews());
        return product;
    }

    private void validateProductCategoryRequest(ProductCategory category) {
        if (Objects.isNull(category) || category.getCategoryName().isBlank() || category.getCategoryDescription().isBlank())
            throw new ApplicationException("Invalid request for creating product category", HttpStatus.BAD_REQUEST);
    }

    private void validateProductRequest(ProductRequest productRequest) {
        if (Objects.isNull(productRequest) || productRequest.getProductName().isBlank() || productRequest.getProductDescription().isBlank() || productRequest.getSpecifications().isBlank() || productRequest.getFeatures().isBlank() || productRequest.getCustomerReviews().isBlank())
            throw new ApplicationException("Invalid request for creating product", HttpStatus.BAD_REQUEST);
    }
}
