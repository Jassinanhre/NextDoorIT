package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.CartQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartQuantityDao extends JpaRepository<CartQuantity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from cart_quantity where user_id=:userId and product_id=:productId", nativeQuery = true)
    public void deleteByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);

    List<CartQuantity> findByUserId(int userId);

    @Query(value = "select * from cart_quantity where user_id=:userId and product_id=:productId", nativeQuery = true)
    CartQuantity findByUserIdAndProductId(int userId, int productId);
}
