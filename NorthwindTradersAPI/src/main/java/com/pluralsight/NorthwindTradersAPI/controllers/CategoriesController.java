package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    // Sample data
    private List<Category> categories = List.of(
            new Category(1, "Beverage"),
            new Category(2, "Condiments"),
            new Category(3, "Confections"),
            new Category(4, "Dairy Products")
    );

    //http://localhost:8080/categories should return a list of all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categories;
    }


    //http://localhost:8080/categories/1 should return a specific category
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categories.stream()
                .filter(c -> c.getCategoryId() == id)
                .findFirst()
                .orElse(null);
    }

    // method to handle get request categories/Search?name=...
    @GetMapping("/categories/search")
    public List<Category> searchCategories(
            @RequestParam(required = false) String name
    ) {
        return categories.stream()
                .filter(p -> name == null || p.getCategoryName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}



