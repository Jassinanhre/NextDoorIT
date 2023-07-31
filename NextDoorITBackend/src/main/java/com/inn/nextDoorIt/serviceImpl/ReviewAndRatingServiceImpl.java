package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.dao.ProductReviewAndRatingDao;
import com.inn.nextDoorIt.dao.ReviewAndRatingDao;
import com.inn.nextDoorIt.dao.TrainingReviewAndRatingsDao;
import com.inn.nextDoorIt.entity.ProductReviewAndRating;
import com.inn.nextDoorIt.entity.ReviewAndRatingsRecord;
import com.inn.nextDoorIt.entity.TrainingReviewRatings;
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

    @Autowired
    private TrainingReviewAndRatingsDao trainingReviewAndRatingsDao;

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

    @Override
    public TrainingReviewRatings saveTrainingReviewAndRating(TrainingReviewRatings record) {
        TrainingReviewRatings savedRecord = trainingReviewAndRatingsDao.save(record);
        if (!Objects.isNull(savedRecord)) {
            return savedRecord;
        }
        throw new ApplicationException("Error while saving review and rating record for IT Training", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void validateTrainingReviewRequest(TrainingReviewRatings record) {
        if (Objects.isNull(record) || Objects.isNull(record.getTrainingId()) || record.getSummery().isBlank() || record.getUsername().isBlank() || Objects.isNull(record.getRating())) {
            throw new ApplicationException("Invalid request for review and ratings", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateProductReviewRequest(ProductReviewAndRating record) {
        if (Objects.isNull(record) || Objects.isNull(record.getProductId()) || record.getSummery().isBlank() || record.getUsername().isBlank() || Objects.isNull(record.getRating())) {
            throw new ApplicationException("Invalid request for review and ratings", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateReviewAndRating(ReviewAndRatingsRecord record) {
        if (Objects.isNull(record) || Objects.isNull(record.getServiceId()) || record.getSummery().isBlank() || record.getUsername().isBlank() || Objects.isNull(record.getRating())) {
            throw new ApplicationException("Invalid request for review and ratings", HttpStatus.BAD_REQUEST);
        }
    }
}

