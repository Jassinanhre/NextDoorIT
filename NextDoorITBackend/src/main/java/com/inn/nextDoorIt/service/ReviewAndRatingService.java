package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import com.inn.nextDoorIt.entity.ReviewAndRatingsRecord;

public interface ReviewAndRatingService {
    public ReviewAndRatingsRecord saveRecord(ReviewAndRatingsRecord record);
    public ProductReviewAndRating saveProductReviewAndRatingRecord(ProductReviewAndRating record);
}
