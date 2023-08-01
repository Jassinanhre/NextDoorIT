package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.ITTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingDao extends JpaRepository<ITTraining, Integer> {

    @Query(value = "select * from it_trainings where training_category=:trainingCategory", nativeQuery = true)
    public List<ITTraining> getByTrainingCategory(@Param("trainingCategory") int trainingCategory);
}
