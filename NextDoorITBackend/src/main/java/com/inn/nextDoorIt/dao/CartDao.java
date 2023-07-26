package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    public Cart findByUserId(int userId);
}
