package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/categories")
public interface CategoriesRest {


    @GetMapping("/allCategories")
    public ResponseEntity<Object> allCategories();
}
