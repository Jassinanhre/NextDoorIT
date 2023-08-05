package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.EnrollTrainingRequest;
import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.entity.TrainingCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/training")
public interface TrainingRest {

    @PostMapping(path = "/saveCategory")
    public ResponseEntity<Object> saveCategory(@RequestBody TrainingCategory category);

    @PostMapping(path = "/saveTraining")
    public ResponseEntity<Object> saveTraining(@RequestBody ITTrainingRequest request);

    @GetMapping(path = "/all")
    public ResponseEntity<Object> getAllTraining();

    @GetMapping(path = "/getDetails")
    public ResponseEntity<Object> getTrainingDetails(@RequestParam("trainingId") int trainingId);

    @PutMapping(path = "/enroll")
    public ResponseEntity<Object> enrollTraining(@RequestBody EnrollTrainingRequest enrollTrainingRequest);

    @GetMapping(path = "/reviewRatings")
    public ResponseEntity<Object> getTrainingReviewAndRatings(@RequestParam("trainingId") int trainingId);

    @GetMapping(path = "/byCategory")
    public ResponseEntity<Object> getTrainingByCategory(@RequestParam("categoryId") int categoryId);
}
