package com.pluralsight.NorthwindTradersAPI;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class App implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override

    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== Northwind Admin Menu ===");
            System.out.println("1. List All Products");
            System.out.println("2. Search Product By Id");
            System.out.println("3. List All Categories");
            System.out.println("4. Search Category By Id");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Product> products = productDao.getAll();
                    System.out.println("\nProducts:");

                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case "2":
                    System.out.print("Enter product id: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Product foundProduct = productDao.getById(id);

                    if (foundProduct != null) {
                        System.out.println("Found: " + foundProduct);
                    } else {
                        System.out.println("Product not found.");
                    }

                    break;

                case "3":
                    List<Category> categories = categoryDao.getAll();
                    System.out.println("\nCategories:");

                    for (Category category : categories) {
                        System.out.println(category);
                    }
                    break;

                case "4":
                    System.out.print("Enter category id: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    Category foundCategory = categoryDao.getById(categoryId);

                    if (foundCategory != null) {
                        System.out.println("Found: " + foundCategory);
                    } else {
                        System.out.println("Category not found.");
                    }
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
