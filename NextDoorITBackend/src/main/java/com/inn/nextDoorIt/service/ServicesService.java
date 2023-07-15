package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.ServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ServicesService {
    List<ServiceModel> getServices(String categoryName);
    ServiceModel saveService(ServiceModel serviceModel);

    List<ServiceModel> getAllServices();


}
