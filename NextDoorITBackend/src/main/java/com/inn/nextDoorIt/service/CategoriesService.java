package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.Category;

import java.util.List;

public interface CategoriesService {
    public List<Category> getAllCategoriesFromDb();
    public Category saveCategoryInDb(Category category);
}
