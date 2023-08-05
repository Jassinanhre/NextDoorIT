package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.POJO.EnrollTrainingRequest;
import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.entity.TrainingCategory;
import com.inn.nextDoorIt.rest.TrainingRest;
import com.inn.nextDoorIt.service.TrainingService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingRestImpl implements TrainingRest {

    @Autowired
    private TrainingService service;

    @Override
    public ResponseEntity<Object> saveCategory(TrainingCategory category) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ApplicationResponse(service.saveTrainingCategory(category), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> saveTraining(ITTrainingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ApplicationResponse(service.saveItTraining(request), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> getAllTraining() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApplicationResponse(service.getAllItTraining(), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> getTrainingDetails(int trainingId) {
        return ResponseEntity.ok(new ApplicationResponse(service.getTrainingDetails(trainingId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> enrollTraining(EnrollTrainingRequest enrollTrainingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(service.enrollTraining(enrollTrainingRequest), HttpStatus.CREATED.value()));
    }


    @Override
    public ResponseEntity<Object> getTrainingReviewAndRatings(int trainingId) {
        return ResponseEntity.ok(new ApplicationResponse(service.getTrainingReviewAndRatings(trainingId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> getTrainingByCategory(int categoryId) {
        return ResponseEntity.ok(new ApplicationResponse(service.trainingByCategoryId(categoryId), HttpStatus.OK.value()));
    }

}
