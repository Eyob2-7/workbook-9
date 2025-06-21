package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    Product getById(int productId);

    Product add(Product product);

    void deleteById(int id);

    void updateById(int id, Product product);
}
