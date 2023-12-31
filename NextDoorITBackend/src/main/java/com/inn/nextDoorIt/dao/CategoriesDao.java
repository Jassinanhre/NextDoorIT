package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesDao extends JpaRepository<Category, Integer> {
}
