package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.*;
import com.inn.nextDoorIt.dao.ProductCategoryDao;
import com.inn.nextDoorIt.dao.ProductDao;
import com.inn.nextDoorIt.dao.ProductReviewAndRatingDao;
import com.inn.nextDoorIt.entity.Product;
import com.inn.nextDoorIt.entity.ProductCategory;
import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductCategoryDao categoryDao;

    @Autowired
    private ProductReviewAndRatingDao productReviewAndRatingDao;
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

    @Override
    @Cacheable(value = "productCache")
    public List<Product> allProducts() {
        List<Product> productsFromDb = productDao.findAll();
        if (!Objects.isNull(productsFromDb) && productsFromDb.size() > 0) {
            return productsFromDb;
        } else {
            throw new ApplicationException("No data found for products", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    @Cacheable(value = "productDetailCache", key = "#productId")
    public Map<String, Object> getProductDetails(int productId) {
        Product product = productDao.findById(productId).orElseThrow(() -> new ApplicationException("No data found for given productId", HttpStatus.NO_CONTENT));
        List<ProductReviewAndRating> productReviewAndRatingList = productReviewAndRatingDao.findAll();
        ProductDetailsModel response = buildProductDetailsResponse(product, productReviewAndRatingList);
        return buildMapResponse(response);
    }

    private Map<String, Object> buildMapResponse(ProductDetailsModel request) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", request.getId());
        response.put("productName", request.getProductName());
        response.put("productDescription", request.getProductDescription());
        response.put("productCategory", request.getProductCategory());
        response.put("features", request.getFeatures());
        response.put("specifications", request.getSpecifications());
        response.put("overallRating", request.getOverallRating());
        response.put("productReviewsAndRatings", request.getProductReviewsAndRatings());
        return response;
    }

    @Override
    @Cacheable(value = "productCache", key = "#categoryId")
    public List<Product> getProductFromCategory(int categoryId) {
        List<Product> productFromDb = productDao.findProductsByCategoryId(categoryId);
        if (!Objects.isNull(productFromDb) && productFromDb.size() > 0) {
            return productFromDb;
        }
        throw new ApplicationException("No product found with given categoryId", HttpStatus.BAD_REQUEST);
    }

    private ProductDetailsModel buildProductDetailsResponse(Product product, List<ProductReviewAndRating> productReviewAndRatingList) {
        ProductDetailsModel response = new ProductDetailsModel();
        response.setId(product.getId());
        response.setProductName(product.getProductName());
        response.setProductDescription(product.getProductDescription());
        response.setProductCategory(product.getProductCategory());
        response.setFeatures(product.getFeatures());
        response.setSpecifications(product.getSpecifications());
        response.setProductReviewsAndRatings(productReviewAndRatingList);
        if (!Objects.isNull(productReviewAndRatingList) && productReviewAndRatingList.size() > 0) {
            response.setOverallRating(getProductOverallRating(productReviewAndRatingList));
        } else {
            response.setOverallRating(null);
        }
        return response;
    }

    private float getProductOverallRating(List<ProductReviewAndRating> productReviewAndRatingList) {

        int noOfReviews = productReviewAndRatingList.size();
        Map<Float, Integer> ratingsCounts = new HashMap<>();
        productReviewAndRatingList.forEach(record -> {
            if (!ratingsCounts.containsKey(record.getRating())) { // if map does not contains key
                ratingsCounts.put(record.getRating(), 1);
            } else {
                int temp = ratingsCounts.get(record.getRating()) + 1;
                ratingsCounts.put(record.getRating(), temp);
            }
        });
        List<Float> keySet = ratingsCounts.keySet().stream().toList();
        float productSums = 0;
        for (int i = 0; i < keySet.size(); i++) {
            productSums += keySet.get(i) * ratingsCounts.get(keySet.get(i));
        }
        float ratingSum = ratingsCounts.values().stream().reduce((first, second) -> first + second).get();
        float overallRating = productSums / ratingSum;
        return overallRating;
    }


    private Product generateProductObject(ProductRequest request, ProductCategory category) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setProductDescription(request.getProductDescription());
        product.setProductCategory(category);
        product.setFeatures(request.getFeatures());
        product.setSpecifications(request.getSpecifications());
        product.setImageId(request.getImageId());
        product.setPrice(request.getPrice());
        return product;
    }

    private void validateProductCategoryRequest(ProductCategory category) {
        if (Objects.isNull(category) || category.getCategoryName().isBlank() || category.getCategoryDescription().isBlank())
            throw new ApplicationException("Invalid request for creating product category", HttpStatus.BAD_REQUEST);
    }

    private void validateProductRequest(ProductRequest productRequest) {
        if (Objects.isNull(productRequest) || productRequest.getProductName().isBlank() || productRequest.getProductDescription().isBlank() || productRequest.getSpecifications().isBlank() || productRequest.getFeatures().isBlank() )
            throw new ApplicationException("Invalid request for creating product", HttpStatus.BAD_REQUEST);
    }
}
