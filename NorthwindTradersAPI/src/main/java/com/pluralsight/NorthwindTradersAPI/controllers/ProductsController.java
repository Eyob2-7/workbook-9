package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    private List<Product> products = List.of(
            new Product(1, "Chai", 1, 18),
            new Product(2, "Tea", 1, 20),
            new Product(3, "Tahini", 2, 12)
    );


    // http://localhost:8080/products should return a list of all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;

    }

    //http://localhost:8080/products/3 should return a specific product
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
    }

    // method to handle get request to products/Search by name,categoryId and price
    @GetMapping("/products/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double price
    ) {
        return products.stream()
                .filter(p -> name == null || p.getProductName().toLowerCase().contains(name.toLowerCase()))
                .filter(p -> categoryId == null || p.getCategoryId() == categoryId)
                .filter(p -> price == null || p.getUnitPrice() == price)
                .toList();
    }

}

