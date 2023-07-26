package com.inn.nextDoorIt.restImpl;

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
}
