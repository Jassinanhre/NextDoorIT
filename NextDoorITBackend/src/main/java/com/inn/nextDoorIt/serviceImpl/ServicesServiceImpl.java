package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.Category;
import com.inn.nextDoorIt.POJO.ServiceModel;
import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import com.inn.nextDoorIt.dao.CategoriesDao;
import com.inn.nextDoorIt.dao.ServicesDao;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.ServicesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    private ServicesDao servicesDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public List<ServiceModel> getServices(int categoryId) {
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
        try {
            category = categoriesDao.findById(serviceModel.getCategoryId()).get();
        } catch (NoSuchElementException e) {
            log.error("NO CATEGORY FOUND FOR REQUESTED CATEGORY ID");
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
    public List<ServiceModel> getAllServices() {
        List<ServiceModel> servicesFromDatabase = servicesDao.findAll();
        if (!Objects.isNull(servicesFromDatabase) && servicesFromDatabase.size() > 0) {
            return servicesFromDatabase;
        } else {
            throw new ApplicationException("No data found", HttpStatus.NOT_FOUND);
        }
    }

    private ServiceModel getServiceModelObject(ServiceModelRequest request, Category category) {
        ServiceModel response = new ServiceModel();
        response.setServiceName(request.getServiceName());
        response.setCategory(category);
        response.setDescription(request.getDescription());
        response.setImageId(request.getImageId());
        response.setUserOverallRating(request.getUserOverallRating());
        return response;
    }

}
