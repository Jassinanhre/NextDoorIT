package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.POJO.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
}
