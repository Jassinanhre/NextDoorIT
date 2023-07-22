package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.ProductReviewAndRating;
import com.inn.nextDoorIt.POJO.ReviewAndRatingsRecord;
import com.inn.nextDoorIt.dao.ProductReviewAndRatingDao;
import com.inn.nextDoorIt.dao.ReviewAndRatingDao;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.ReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ReviewAndRatingServiceImpl implements ReviewAndRatingService {
    @Autowired
    private ReviewAndRatingDao reviewAndRatingDao;

    @Autowired
    private ProductReviewAndRatingDao productReviewAndRatingDao;

    @Override
    public ReviewAndRatingsRecord saveRecord(ReviewAndRatingsRecord record) {
        ReviewAndRatingsRecord savedRecord = reviewAndRatingDao.save(record);
        if (!Objects.isNull(savedRecord)) {
            return savedRecord;
        }
        throw new ApplicationException("Unable to save review and ratting record", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ProductReviewAndRating saveProductReviewAndRatingRecord(ProductReviewAndRating record) {
        ProductReviewAndRating savedRecord = productReviewAndRatingDao.save(record);
        if (!Objects.isNull(savedRecord)) {
            return savedRecord;
        } else {
            throw new ApplicationException("Error saving product review and rating record", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
