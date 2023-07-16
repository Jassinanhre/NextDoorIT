package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.POJO.Category;
import com.inn.nextDoorIt.rest.CategoriesRest;
import com.inn.nextDoorIt.service.CategoriesService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesRestImpl implements CategoriesRest {
    @Autowired
    private CategoriesService service;

    @Override
    public ResponseEntity<Object> allCategories() {
        return ResponseEntity.ok(new ApplicationResponse(service.getAllCategoriesFromDb(), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> saveCategory(Category category) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ApplicationResponse(service.saveCategoryInDb(category), HttpStatus.CREATED.value()));
    }
}
