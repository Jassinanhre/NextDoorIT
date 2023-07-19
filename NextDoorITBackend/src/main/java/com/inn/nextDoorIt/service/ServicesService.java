package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.ServiceModel;
import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import com.inn.nextDoorIt.POJO.ServiceRequestRecord;

import java.util.List;


public interface ServicesService {
    List<ServiceModel> getServices(int categoryId);

    ServiceModel saveService(ServiceModelRequest serviceModel);

    List<ServiceModel> getAllServices();

    ServiceModel getServiceDetails(int serviceId);

    String saveRequestedServiceRecord(ServiceRequestRecord requestRecord);
}
