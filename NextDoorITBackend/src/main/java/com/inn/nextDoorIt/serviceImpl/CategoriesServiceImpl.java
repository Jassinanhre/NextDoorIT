package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.Category;
import com.inn.nextDoorIt.dao.CategoriesDao;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    @Cacheable(value = "categoryCache")
    public List<Category> getAllCategoriesFromDb() {
        List<Category> categoriesFromDb = categoriesDao.findAll();
        if (!Objects.isNull(categoriesFromDb) && categoriesFromDb.size() > 0) {
            return categoriesFromDb;
        } else {
            throw new ApplicationException("No data found for categories", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Category saveCategoryInDb(Category category) {
        validateCategoryRequest(category);
        Category savedResponse = categoriesDao.save(category);
        if (!Objects.isNull(savedResponse)) {
            return savedResponse;
        }
        throw new ApplicationException("Error while saving category request", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void validateCategoryRequest(Category category) {
        if (category.getCategoryName().isBlank() || category.getDescription().isBlank()) {
            throw new ApplicationException("Invalid category request", HttpStatus.BAD_REQUEST);
        }
    }
}