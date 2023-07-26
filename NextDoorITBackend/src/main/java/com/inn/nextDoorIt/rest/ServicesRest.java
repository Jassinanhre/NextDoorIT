package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.ServiceModelRequest;
import com.inn.nextDoorIt.entity.ServiceRequestRecord;
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

    @GetMapping(path = "/serviceDetails")
    public ResponseEntity<Object> getServiceDetails(@RequestParam("serviceId") int serviceId);

    @PostMapping(path = "/requestService")
    public ResponseEntity<Object> requestService(@RequestBody ServiceRequestRecord requestRecord);
}
