package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.*;
import com.inn.nextDoorIt.dao.CategoriesDao;
import com.inn.nextDoorIt.dao.ReviewAndRatingDao;
import com.inn.nextDoorIt.dao.ServiceRequestRecordsDao;
import com.inn.nextDoorIt.dao.ServicesDao;
import com.inn.nextDoorIt.entity.Category;
import com.inn.nextDoorIt.entity.ReviewAndRatingsRecord;
import com.inn.nextDoorIt.entity.ServiceModel;
import com.inn.nextDoorIt.entity.ServiceRequestRecord;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.ServicesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    private ServicesDao servicesDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private ServiceRequestRecordsDao requestRecordsDao;


    @Autowired
    private ReviewAndRatingDao reviewAndRatingDao;

    @Override
    @Cacheable(value = "serviceCache", key = "#categoryId")
    public List<ServiceModel> getServices(int categoryId) {
        if (Objects.isNull(categoryId))
            throw new ApplicationException("No category id specified", HttpStatus.BAD_REQUEST);
        List<ServiceModel> servicesFromDb = servicesDao.findServicesByCategories(categoryId);
        if (!Objects.isNull(servicesFromDb) && servicesFromDb.size() > 0) {
            return servicesFromDb;
        } else {
            throw new ApplicationException("No service is found for the given category id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ServiceModel saveService(ServiceModelRequest serviceModel) {
        Category category = null;
        validateServiceRequest(serviceModel);
        try {
            category = categoriesDao.findById(serviceModel.getCategoryId()).get();
        } catch (NoSuchElementException e) {
            throw new ApplicationException("No category is found for given category id", HttpStatus.NOT_FOUND);
        }
        ServiceModel serviceModelObject = getServiceModelObject(serviceModel, category);
        ServiceModel savedResponse = servicesDao.save(serviceModelObject);
        if (savedResponse != null) {
            return savedResponse;
        } else {
            throw new ApplicationException("Error saving service request : ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Cacheable(value = "serviceCache")
    public List<ServiceModel> getAllServices() {
        List<ServiceModel> servicesFromDatabase = servicesDao.findAll();
        if (!Objects.isNull(servicesFromDatabase) && servicesFromDatabase.size() > 0) {
            return servicesFromDatabase;
        } else {
            throw new ApplicationException("No data found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Cacheable(value = "serviceDetailCache", key = "#serviceId")
    public Map<String, Object> getServiceDetails(int serviceId) {
        ServiceModel service = servicesDao.findById(serviceId).orElseThrow(() -> new ApplicationException("No service found for requested service id", HttpStatus.BAD_REQUEST));
        List<ReviewAndRatingsRecord> reviewAndRatingsRecords = reviewAndRatingDao.findByServiceId(serviceId);
        ServiceDetailsModel response = buildServiceDetailsResponse(service, reviewAndRatingsRecords);
        HashMap<String, Object> responseMap = new HashMap<>();
        // RETURNING RESPONSE IN FORM OF MAP BECAUSE OF REDIS CACHE, BECAUSE
        // REDIS CACHE IS CACHING RESPONSE IN FORM OF HASHMAP SO IT WILL GIVE CLASS CAST EXCEPTION OTHERWISE
        responseMap.put("id", response.getId());
        responseMap.put("serviceName", response.getServiceName());
        responseMap.put("description", response.getDescription());
        responseMap.put("category", response.getCategory());
        responseMap.put("userOverallRating", response.getUserOverallRating());
        responseMap.put("imageId", response.getImageId());
        responseMap.put("price", response.getPrice());
        responseMap.put("duration", response.getDuration());
        responseMap.put("reviewRatings", response.getReviewRatings());
        return responseMap;
    }


    private ServiceDetailsModel buildServiceDetailsResponse(ServiceModel service, List<ReviewAndRatingsRecord> reviews) {
        ServiceDetailsModel response = new ServiceDetailsModel();
        response.setId(service.getId());
        response.setServiceName(service.getServiceName());
        response.setDescription(service.getDescription());
        response.setDuration(service.getDuration());
        response.setCategory(service.getCategory());
        if (!Objects.isNull(reviews) && reviews.size() > 0) {
            response.setUserOverallRating(calculateOverallRating(reviews));
        } else {
            response.setUserOverallRating(null);
        }
        response.setReviewRatings(reviews);
        response.setPrice(service.getPrice());
        response.setImageId(service.getImageId());
        return response;
    }

    private float calculateOverallRating(List<ReviewAndRatingsRecord> reviews) {
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
        double overallRating = productSums / ratingSum;
        overallRating = Math.ceil(overallRating);
        return (float) overallRating;
    }

    @Override
    public String saveRequestedServiceRecord(ServiceRequestRecord requestRecord) {
        validateServiceRequestRecord(requestRecord);
        ServiceRequestRecord savedRecord = requestRecordsDao.save(requestRecord);
        if (savedRecord != null) {
            return "Service request saved successfully";
        }
        throw new ApplicationException("Error requesting for service", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void validateServiceRequestRecord(ServiceRequestRecord serviceRequestRecord) {
        if (serviceRequestRecord.getServiceName().isBlank() || serviceRequestRecord.getTrainingType().isBlank() || serviceRequestRecord.getUserQuery().isBlank() || serviceRequestRecord.getUserEmail().isBlank()) {
            throw new ApplicationException("Invalid request for service request", HttpStatus.BAD_REQUEST);
        }
    }

    private ServiceModel getServiceModelObject(ServiceModelRequest request, Category category) {
        ServiceModel response = new ServiceModel();
        response.setServiceName(request.getServiceName());
        response.setCategory(category);
        response.setDescription(request.getDescription());
        response.setImageId(request.getImageId());
        response.setPrice(request.getPrice());
        response.setDuration(request.getDuration());
        return response;
    }

    private void validateServiceRequest(ServiceModelRequest serviceModelRequest) {
        if (serviceModelRequest.getServiceName().isBlank() || serviceModelRequest.getDescription().isBlank() || Objects.isNull(serviceModelRequest.getCategoryId()) || serviceModelRequest.getImageId().isBlank() || Objects.isNull(serviceModelRequest.getDuration()) || Objects.isNull(serviceModelRequest.getPrice()))
            throw new ApplicationException("Invalid Service Request", HttpStatus.BAD_REQUEST);
    }
}
