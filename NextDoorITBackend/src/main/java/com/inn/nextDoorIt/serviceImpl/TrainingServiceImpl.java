package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.dao.TrainingCategoryDao;
import com.inn.nextDoorIt.dao.TrainingDao;
import com.inn.nextDoorIt.entity.ITTraining;
import com.inn.nextDoorIt.entity.TrainingCategory;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingCategoryDao categoryDao;

    @Autowired
    private TrainingDao trainingDao;

    @Override
    public TrainingCategory saveTrainingCategory(TrainingCategory category) {
        validateCategoryRequest(category);
        TrainingCategory dbResponse = categoryDao.save(category);
        if (!Objects.isNull(dbResponse)) {
            return dbResponse;
        }
        throw new ApplicationException("Error while saving category in database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ITTraining saveItTraining(ITTrainingRequest request) {
        TrainingCategory category = categoryDao.findById(request.getCategoryId()).orElseThrow(() -> new ApplicationException("No category found for request category id", HttpStatus.BAD_REQUEST));
        ITTraining toSave = buildItTrainingObject(request, category);
        ITTraining savedResponse = trainingDao.save(toSave);
        if (!Objects.isNull(savedResponse)) {
            return savedResponse;
        }
        throw new ApplicationException("Error while saving training request", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ITTraining buildItTrainingObject(ITTrainingRequest request, TrainingCategory category) {
        ITTraining training = new ITTraining();
        training.setTrainingCategory(category);
        training.setName(request.getName());
        training.setDescription(request.getDescription());
        training.setDuration(request.getDuration());
        training.setPrice(request.getPrice());
        training.setImageId(request.getImageId());
        return training;
    }

    private void validateCategoryRequest(TrainingCategory category) {
        if (Objects.isNull(category) || category.getCategoryName().isBlank() || category.getDescription().isBlank()) {
            throw new ApplicationException("Invalid request for creating training category", HttpStatus.BAD_REQUEST);
        }
    }
}
