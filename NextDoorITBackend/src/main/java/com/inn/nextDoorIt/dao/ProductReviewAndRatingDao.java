package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewAndRatingDao extends JpaRepository<ProductReviewAndRating, Integer> {
    List<ProductReviewAndRating> findByProductId(int productId);
}
