package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    private CategoryDao categoryDao;

    // Constructor injection
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoriesById(@PathVariable int id) {
        return categoryDao.getById(id);
    }
}


