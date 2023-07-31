package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.ServiceModel;
import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import com.inn.nextDoorIt.entity.ServiceRequestRecord;

import java.util.List;
import java.util.Map;


public interface ServicesService {
    List<ServiceModel> getServices(int categoryId);

    ServiceModel saveService(ServiceModelRequest serviceModel);

    List<ServiceModel> getAllServices();

    Map<String, Object> getServiceDetails(int serviceId);

    String saveRequestedServiceRecord(ServiceRequestRecord requestRecord);
}
