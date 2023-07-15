package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.POJO.ServiceModel;
import com.inn.nextDoorIt.dao.ServicesDao;
import com.inn.nextDoorIt.rest.ServicesRest;
import com.inn.nextDoorIt.service.ServicesService;
import com.inn.nextDoorIt.serviceImpl.ServicesServiceImpl;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRestImpl implements ServicesRest {
    @Autowired
    private ServicesService service;


    @Override
    public ResponseEntity<Object> getServices(String category) {
        return ResponseEntity.ok(new ApplicationResponse(service.getServices(category), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> saveService(ServiceModel serviceModel) {
        return ResponseEntity.ok(new ApplicationResponse(service.saveService(serviceModel), HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseEntity<Object> allServices() {
        return ResponseEntity.ok(new ApplicationResponse(service.getAllServices(), HttpStatus.OK.value()));
    }
}
