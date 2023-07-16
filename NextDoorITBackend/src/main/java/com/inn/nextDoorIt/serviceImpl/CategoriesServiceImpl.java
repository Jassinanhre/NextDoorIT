package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.POJO.Category;
import com.inn.nextDoorIt.dao.CategoriesDao;
import com.inn.nextDoorIt.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public List<Category> getAllCategoriesFromDb() {
        List<Category> categoriesFromDb = categoriesDao.findAll();
        if (!Objects.isNull(categoriesFromDb) && categoriesFromDb.size() > 0) {
            return categoriesFromDb;
        } else {
            throw new RuntimeException("NO CATEGORIES FOUND IN DATABASE ");
            // custome exception handeling code will be here
        }
    }

    @Override
    public Category saveCategoryInDb(Category category) {
        Category savedResponse = categoriesDao.save(category);
        if (!Objects.isNull(savedResponse)) {
            return savedResponse;
        }
        throw new RuntimeException("CATEGORY NOT SAVED IN DATABASE");
    }
}