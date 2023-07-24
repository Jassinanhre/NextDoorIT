package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.POJO.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query(value = "select * from products where product_category_id=:categoryId", nativeQuery = true)
    public List<Product> findProductsByCategoryId(@Param("categoryId") int categoryId);
}
