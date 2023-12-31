package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import com.inn.nextDoorIt.entity.ReviewAndRatingsRecord;
import com.inn.nextDoorIt.entity.TrainingReviewRatings;
import com.inn.nextDoorIt.rest.ReviewRatingsRest;
import com.inn.nextDoorIt.service.ReviewAndRatingService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewAndRatingsRestImpl implements ReviewRatingsRest {
    @Autowired
    private ReviewAndRatingService service;

    @Override
    public ResponseEntity<Object> saveReviewAndRatingRecord(ReviewAndRatingsRecord record) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(service.saveRecord(record), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> saveProductReviewAndRating(ProductReviewAndRating record) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(service.saveProductReviewAndRatingRecord(record), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> saveTrainingReviewAndRating(TrainingReviewRatings record) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse(service.saveTrainingReviewAndRating(record), HttpStatus.CREATED.value()));
    }


}
