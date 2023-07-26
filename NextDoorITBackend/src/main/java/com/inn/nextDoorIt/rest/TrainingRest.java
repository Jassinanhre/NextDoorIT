package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.entity.ITTraining;
import com.inn.nextDoorIt.entity.TrainingCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/training")
public interface TrainingRest {

    @PostMapping(path = "/saveCategory")
    public ResponseEntity<Object> saveCategory(@RequestBody TrainingCategory category);

    @PostMapping(path = "/saveTraining")
    public ResponseEntity<Object> saveTraining(@RequestBody ITTrainingRequest request);
}
