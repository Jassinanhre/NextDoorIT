package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.ReviewAndRatingsRecord;
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

    @Override
    public ReviewAndRatingsRecord saveRecord(ReviewAndRatingsRecord record) {
        ReviewAndRatingsRecord savedRecord = reviewAndRatingDao.save(record);
        if (!Objects.isNull(savedRecord)) {
            return savedRecord;
        }
        throw new ApplicationException("Unable to save review and ratting record", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
