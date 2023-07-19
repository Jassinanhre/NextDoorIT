package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.ServiceDetails;
import com.inn.nextDoorIt.POJO.ServiceModel;
import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ServicesService {
    List<ServiceModel> getServices(int categoryId);

    ServiceModel saveService(ServiceModelRequest serviceModel);

    List<ServiceModel> getAllServices();

    List<ServiceDetails> getServiceDetails();


}
