package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.EnrollmentResponse;
import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.entity.ITTraining;
import com.inn.nextDoorIt.entity.TrainingCategory;

import java.util.List;
import java.util.Map;

public interface TrainingService {
    public TrainingCategory saveTrainingCategory(TrainingCategory category);

    public ITTraining saveItTraining(ITTrainingRequest request);

    public List<ITTraining> getAllItTraining();

    public Map<String, Object> getTrainingDetails(int trainingId);

    public EnrollmentResponse enrollTraining(int userId, int trainingId);
}
