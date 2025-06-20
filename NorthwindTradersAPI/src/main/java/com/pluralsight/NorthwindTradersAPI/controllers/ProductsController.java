package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    private ProductDao productDao;

    // Constructor injection
    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.getById(id);
    }
}
