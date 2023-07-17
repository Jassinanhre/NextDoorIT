package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.ServiceModel;
import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/service")
public interface ServicesRest {

    @RequestMapping(path = "/category")
    public ResponseEntity<Object> getServices(@RequestParam("categoryId") int categoryId);

    @RequestMapping(path = "/save")
    public ResponseEntity<Object> saveService(@RequestBody(required = true) ServiceModelRequest serviceModel);

    @RequestMapping(path = "/all")
    public ResponseEntity<Object> allServices();
}
