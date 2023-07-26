package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.TrainingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingCategoryDao extends JpaRepository<TrainingCategory, Integer> {
}
