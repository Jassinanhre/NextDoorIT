package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import com.inn.nextDoorIt.entity.ReviewAndRatingsRecord;
import com.inn.nextDoorIt.entity.TrainingReviewRatings;

public interface ReviewAndRatingService {
    public ReviewAndRatingsRecord saveRecord(ReviewAndRatingsRecord record);

    public ProductReviewAndRating saveProductReviewAndRatingRecord(ProductReviewAndRating record);

    public TrainingReviewRatings saveTrainingReviewAndRating(TrainingReviewRatings record);
}
