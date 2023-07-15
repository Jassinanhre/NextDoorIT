package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.ServiceModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/service")
public interface ServicesRest {

    @RequestMapping(path = "/{category}")
    public ResponseEntity<Object> getServices(@PathVariable("category") String category);

    @RequestMapping(path = "/save")
    public ResponseEntity<Object> saveService(@RequestBody(required = true) ServiceModel serviceModel);

    @RequestMapping(path="/all")
    public ResponseEntity<Object> allServices();
}
