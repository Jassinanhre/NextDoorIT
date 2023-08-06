package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.EnrollTrainingRequest;
import com.inn.nextDoorIt.POJO.EnrollmentResponse;
import com.inn.nextDoorIt.POJO.ITTrainingRequest;
import com.inn.nextDoorIt.dao.TrainingCategoryDao;
import com.inn.nextDoorIt.dao.TrainingDao;
import com.inn.nextDoorIt.dao.TrainingReviewAndRatingsDao;
import com.inn.nextDoorIt.dao.UserDao;
import com.inn.nextDoorIt.entity.ITTraining;
import com.inn.nextDoorIt.entity.TrainingCategory;
import com.inn.nextDoorIt.entity.TrainingReviewRatings;
import com.inn.nextDoorIt.entity.User;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingCategoryDao categoryDao;

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private TrainingReviewAndRatingsDao reviewAndRatingsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public TrainingCategory saveTrainingCategory(TrainingCategory category) {
        validateCategoryRequest(category);
        TrainingCategory dbResponse = categoryDao.save(category);
        if (!Objects.isNull(dbResponse)) {
            return dbResponse;
        }
        throw new ApplicationException("Error while saving category in database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ITTraining saveItTraining(ITTrainingRequest request) {
        validateItTrainingRequest(request);
        TrainingCategory category = categoryDao.findById(request.getCategoryId()).orElseThrow(() -> new ApplicationException("No category found for request category id", HttpStatus.BAD_REQUEST));
        ITTraining toSave = buildItTrainingObject(request, category);
        ITTraining savedResponse = trainingDao.save(toSave);
        if (!Objects.isNull(savedResponse)) {
            return savedResponse;
        }
        throw new ApplicationException("Error while saving training request", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Cacheable(value = "trainingCache")
    public List<ITTraining> getAllItTraining() {
        List<ITTraining> allTrainingRecords = trainingDao.findAll();
        if (!Objects.isNull(allTrainingRecords) && allTrainingRecords.size() > 0) {
            return allTrainingRecords;
        }
        throw new ApplicationException("No records found for ITTraining", HttpStatus.NO_CONTENT);
    }

    @Override
    @Cacheable(value = "trainingCache", key = "#trainingId")
    public Map<String, Object> getTrainingDetails(int trainingId) {
        ITTraining responseFromDb = trainingDao.findById(trainingId).orElseThrow(() -> new ApplicationException("No training record found for requested trainingId", HttpStatus.BAD_REQUEST));
        List<TrainingReviewRatings> reviewAndRatings = reviewAndRatingsDao.findByTrainingId(trainingId);
        return generateTrainingDetailsResponse(responseFromDb, reviewAndRatings);
    }

    @Override
    public EnrollmentResponse enrollTraining(EnrollTrainingRequest enrollTrainingRequest) {
        User user = userDao.findByEmailId(enrollTrainingRequest.getEmail());
        if (Objects.isNull(user)) {
            throw new ApplicationException("No user found with requested email", HttpStatus.BAD_REQUEST);
        }
        ITTraining training = trainingDao.findById(enrollTrainingRequest.getTrainingId()).orElseThrow(() -> new ApplicationException("No IT Training found for requested trainingId", HttpStatus.BAD_REQUEST));
        List<ITTraining> alreadyEnrolled = user.getUserTakenTrainings();
        if (!Objects.isNull(alreadyEnrolled) && alreadyEnrolled.size() > 0) { // IF THERE ARE SOME TRAININGS ALREADY ENROLLED
            List<ITTraining> duplicateTrainings = alreadyEnrolled.stream().filter(currentTraining -> training.getId() == enrollTrainingRequest.getTrainingId()).collect(Collectors.toList());
            if (!Objects.isNull(duplicateTrainings) && duplicateTrainings.size() > 0) {
                throw new ApplicationException("The requested training is alredady enrolled by user", HttpStatus.BAD_REQUEST);
            }
        }
//      List<User> enrolledUsersForTraining = training.getUsers();
        List<ITTraining> updatedTraining = null;
        List<User> updatedUserList = null;
        if (Objects.isNull(alreadyEnrolled) || alreadyEnrolled.size() == 0) { // IF THERE IS NO TRAINING ENROLLED ALREADY
            updatedTraining = new ArrayList<>();
            updatedTraining.add(training);
            user.setUserTakenTrainings(updatedTraining);
        } else {
            updatedTraining = new ArrayList<>();
            updatedTraining.addAll(alreadyEnrolled);
            updatedTraining.add(training);
            user.setUserTakenTrainings(updatedTraining);
        }

        User updatedUserResponse = userDao.save(user);// saved user
        ITTraining trainingResponse = trainingDao.save(training);
        if (!Objects.isNull(updatedUserResponse)) {
            EnrollmentResponse response = new EnrollmentResponse();
            response.setUserName(updatedUserResponse.getName());
            response.setEmail(updatedUserResponse.getEmail());
            response.setEnrolledTrainings(user.getUserTakenTrainings());
            return response;
        }
        throw new ApplicationException("Unable to enroll the it training for the requested user", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Cacheable(value = "reviewRatingCache", key = "#trainingId")
    public List<TrainingReviewRatings> getTrainingReviewAndRatings(int trainingId) {
        // getting all the review and ratings for the training with requested training id
        List<TrainingReviewRatings> reviewRatings = reviewAndRatingsDao.findByTrainingId(trainingId);
        if (!Objects.isNull(reviewRatings) && reviewRatings.size() > 0) {
            return reviewRatings;
        }
        throw new ApplicationException("No review and rating found for the requested trainingId", HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<ITTraining> trainingByCategoryId(int categoryId) {
        List<ITTraining> itTrainings = trainingDao.getByTrainingCategory(categoryId);
        if (!Objects.isNull(itTrainings) && itTrainings.size() > 0) {
            return itTrainings;
        }
        throw new ApplicationException("No data found for given categoryId", HttpStatus.BAD_REQUEST);
    }

    private void verifyDuplicateEnrollment(List<ITTraining> alreadyEnrolled, int trainingId) {
        List<ITTraining> matchingRecords = alreadyEnrolled.stream().filter(itTraining -> {
            return itTraining.getId() == trainingId;
        }).collect(Collectors.toList());
        if (!Objects.isNull(matchingRecords) && matchingRecords.size() > 0) {
            throw new ApplicationException("This it training is already enrolled by candidate", HttpStatus.BAD_REQUEST);
        }
    }

    private Map<String, Object> generateTrainingDetailsResponse(ITTraining training, List<TrainingReviewRatings> reviewAndRatings) {
        Map<String, Object> response = new HashMap();
        response.put("id", training.getId());
        response.put("name", training.getName());
        response.put("description", training.getDescription());
        response.put("price", training.getPrice());
        response.put("duration", training.getDuration());
        response.put("image_id", training.getImageId());
        response.put("objective", training.getObjective());
        response.put("prerequisites", training.getPrerequisites());
        response.put("syllabus", training.getSyllabus());
        response.put("category", training.getTrainingCategory());
        if (Objects.isNull(reviewAndRatings) || reviewAndRatings.size() == 0) {
            response.put("overallRating", 0.0);
        } else {
            response.put("overallRating", getOverallRating(reviewAndRatings));
        }
        return response;
    }

    private Float getOverallRating(List<TrainingReviewRatings> reviews) {
        int noOfReviews = reviews.size();
        Map<Float, Integer> ratingsCounts = new HashMap<>();
        reviews.forEach(record -> {
            if (!ratingsCounts.containsKey(record.getRating())) { // if map does not contains key
                ratingsCounts.put(record.getRating(), 1);
            } else {
                int temp = ratingsCounts.get(record.getRating()) + 1;
                ratingsCounts.put(record.getRating(), temp);
            }
        });
        List<Float> keySet = ratingsCounts.keySet().stream().toList();
        float productSums = 0;
        for (int i = 0; i < keySet.size(); i++) {
            productSums += keySet.get(i) * ratingsCounts.get(keySet.get(i));
        }
        float ratingSum = ratingsCounts.values().stream().reduce((first, second) -> first + second).get();
        float overallRating = productSums / ratingSum;
        overallRating = Math.round(overallRating);
        return overallRating;
    }

    private ITTraining buildItTrainingObject(ITTrainingRequest request, TrainingCategory category) {
        ITTraining training = new ITTraining();
        training.setTrainingCategory(category);
        training.setName(request.getName());
        training.setDescription(request.getDescription());
        training.setDuration(request.getDuration());
        training.setPrice(request.getPrice());
        training.setImageId(request.getImageId());
        training.setObjective(request.getObjective());
        training.setSyllabus(request.getSyllabus());
        training.setPrerequisites(request.getPrerequisites());
        return training;
    }

    private void validateCategoryRequest(TrainingCategory category) {
        if (Objects.isNull(category) || category.getCategoryName().isBlank() || category.getDescription().isBlank()) {
            throw new ApplicationException("Invalid request for creating training category", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateItTrainingRequest(ITTrainingRequest request) {
        if (Objects.isNull(request) || request.getObjective().isBlank() || request.getDescription().isBlank() || request.getName().isBlank() || request.getPrerequisites().isBlank() || Objects.isNull(request.getDuration()) || Objects.isNull(request.getCategoryId()) || request.getImageId().isBlank() || request.getSyllabus().isBlank()) {
            throw new ApplicationException("Invalid request for IT Training", HttpStatus.BAD_REQUEST);
        }
    }
}
