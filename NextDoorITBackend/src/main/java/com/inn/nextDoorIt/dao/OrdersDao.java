package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao extends JpaRepository<OrderDetails, Integer> {
    OrderDetails findByUserId(int userId);
}
