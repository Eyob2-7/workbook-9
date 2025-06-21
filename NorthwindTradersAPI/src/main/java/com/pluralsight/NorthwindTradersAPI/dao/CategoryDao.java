package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getAll();

    Category getById(int categoryId);

    Category add(Category category);

    void deleteById(int id);

    void update(int id, Category category);
}
