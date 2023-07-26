package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.entity.ITTraining;
import com.inn.nextDoorIt.entity.TrainingCategory;

public interface TrainingService {
    public TrainingCategory saveTrainingCategory(TrainingCategory category);

    public ITTraining saveItTraining(ITTrainingRequest request);
}
