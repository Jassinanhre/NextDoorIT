package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import com.inn.nextDoorIt.entity.ReviewAndRatingsRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/feedback")
public interface ReviewRatingsRest {
    @PostMapping(path = "/save")
    public ResponseEntity<Object> saveReviewAndRatingRecord(@RequestBody ReviewAndRatingsRecord record);

    @PostMapping(path = "/save/productReview")
    public ResponseEntity<Object> saveProductReviewAndRating(@RequestBody ProductReviewAndRating record);
}
