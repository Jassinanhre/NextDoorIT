package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import com.inn.nextDoorIt.entity.ServiceRequestRecord;
import com.inn.nextDoorIt.rest.ServicesRest;
import com.inn.nextDoorIt.service.ServicesService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRestImpl implements ServicesRest {
    @Autowired
    private ServicesService service;


    @Autowired
    private CacheManager cacheManager;

    @Override
    public ResponseEntity<Object> getServices(int categoryId) {
        return ResponseEntity.ok(new ApplicationResponse(service.getServices(categoryId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> saveService(ServiceModelRequest serviceModel) {
        return ResponseEntity.ok(new ApplicationResponse(service.saveService(serviceModel), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> allServices() {
        return ResponseEntity.ok(new ApplicationResponse(service.getAllServices(), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> getServiceDetails(int serviceId) {
        return ResponseEntity.ok(new ApplicationResponse(service.getServiceDetails(serviceId), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> requestService(ServiceRequestRecord requestRecord) {
        return ResponseEntity.ok(new ApplicationResponse(service.saveRequestedServiceRecord(requestRecord), HttpStatus.OK.value()));
    }
}
