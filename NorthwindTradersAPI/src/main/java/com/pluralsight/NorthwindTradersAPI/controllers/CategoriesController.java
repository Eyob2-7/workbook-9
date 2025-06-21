package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/api/categories")
    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }

    @GetMapping("/api/categories/{id}")
    public Category getCategoriesById(@PathVariable int id) {
        return categoryDao.getById(id);
    }

    @PostMapping("/api/categories")
    public Category add(@RequestBody Category category){
        return categoryDao.add(category);
    }

    @PutMapping("/api/categories/{id}")
    public void update(@PathVariable int id, @RequestBody Category category){
        categoryDao.update(id, category);
    }

    @DeleteMapping("/api/categories/{id}")
    public void delete(@PathVariable int id){
        categoryDao.deleteById(id);
    }
}


