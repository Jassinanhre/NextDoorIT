package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.TrainingReviewRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingReviewAndRatingsDao extends JpaRepository<TrainingReviewRatings, Integer> {
    public List<TrainingReviewRatings> findByTrainingId(int trainingId);
}
