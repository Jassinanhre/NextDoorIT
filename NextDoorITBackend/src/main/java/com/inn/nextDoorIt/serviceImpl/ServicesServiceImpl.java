package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.ServiceModel;
import com.inn.nextDoorIt.dao.ServicesDao;
import com.inn.nextDoorIt.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    private ServicesDao servicesDao;

    @Override
    public List<ServiceModel> getServices(String categoryName) {
        List<ServiceModel> servicesFromDb = servicesDao.findServicesByCategories(categoryName);
        if (!Objects.isNull(servicesFromDb) && servicesFromDb.size() > 0) {
            return servicesFromDb;
        } else {
            throw new RuntimeException("No service is found for the given category name");
        }
    }

    @Override
    public ServiceModel saveService(ServiceModel serviceModel) {
        ServiceModel savedResponse = servicesDao.save(serviceModel);
        if (savedResponse != null) {
            return savedResponse;
        } else {
            throw new RuntimeException("SERVICE IS NOT SAVED");
            // HERE WILL BE EXCEPTION HANDELING CODE
        }
    }

    @Override
    public List<ServiceModel> getAllServices() {
        List<ServiceModel> servicesFromDatabase = servicesDao.findAll();
        if (!Objects.isNull(servicesFromDatabase) && servicesFromDatabase.size() > 0) {
            return servicesFromDatabase;
        } else {
            throw new RuntimeException("NO SERVICES FOUND");
        }
    }
}
