package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/categories")
public interface CategoriesRest {


    @GetMapping("/allCategories")
    public ResponseEntity<Object> allCategories();

    @PostMapping("/save")
    public ResponseEntity<Object> saveCategory(@RequestBody Category category);
}
