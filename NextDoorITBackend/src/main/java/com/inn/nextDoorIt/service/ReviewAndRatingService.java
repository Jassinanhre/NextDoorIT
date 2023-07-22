package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.ProductReviewAndRating;
import com.inn.nextDoorIt.POJO.ReviewAndRatingsRecord;

public interface ReviewAndRatingService {
    public ReviewAndRatingsRecord saveRecord(ReviewAndRatingsRecord record);
    public ProductReviewAndRating saveProductReviewAndRatingRecord(ProductReviewAndRating record);
}
