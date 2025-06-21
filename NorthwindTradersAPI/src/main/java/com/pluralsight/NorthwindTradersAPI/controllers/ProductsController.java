package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductDao productDao;



    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.getById(id);
    }

    @PostMapping("/api/products")
    public Product add(@RequestBody Product product){
        return productDao.add(product);
    }

    @PutMapping("/api/products/{id}")
    public void update(@PathVariable int id, @RequestBody Product product){
        productDao.updateById(id, product);
    }

    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable int id){
        productDao.deleteById(id);
    }
}
