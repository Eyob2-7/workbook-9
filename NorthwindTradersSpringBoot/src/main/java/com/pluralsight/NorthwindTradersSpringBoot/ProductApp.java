package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ProductApp implements CommandLineRunner {

    //create an instance of our productDao
    @Autowired
    private ProductDao productDao;

    @Override
    public void run(String... args) throws Exception {

        // We create a Scanner object so we can read user input from the console.
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // Print the menu options to the screen.
            System.out.println("\n=== Film Admin Menu ===");
            System.out.println("1. List All Products");
            System.out.println("2. Add Product");
            System.out.println("3. Delete a Product");
            System.out.println("4. Update Product");
            System.out.println("5. Search By Keyword");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            // Read the user's choice as a String.
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Call the DAO to get a list of all products.
                    List<Product> products = productDao.getAll();

                    // Print the products to the screen.
                    System.out.println("\nProducts:");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case "2":
                    // Ask the user for the product's id.
                    System.out.print("Enter product id: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    // Ask the user for the product's name.
                    System.out.println("Enter product name");
                    String name = scanner.nextLine();

                    // Ask the user the category id
                    System.out.println("Enter category ID");
                    int category = Integer.parseInt(scanner.nextLine());

                    //sk the user for the product's price
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());


                    // Create a new Product object.
                    Product newProduct = new Product(id, name, category, price);

                    // Add the new product to the DAO (which stores it in memory).
                    productDao.add(newProduct);

                    // Let the user know that the product was added.
                    System.out.println("Product added successfully.");
                    break;

                case "3":
                    System.out.print("Enter the product ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());

                    productDao.deleteById(deleteId);
                    System.out.println("🗑️ Product deleted (if it existed).");
                    break;

                case "4":
                    System.out.print("Enter the product ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter new category ID: ");
                    int newCategory = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter new price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());

                    Product updatedProduct = new Product(updateId, newName, newCategory, newPrice);
                    productDao.update(updatedProduct);

                    System.out.println("✏️ Product updated.");
                    break;

                case "5":
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();

                    List<Product> matchingProducts = productDao.searchByName(keyword);

                    if (matchingProducts.isEmpty()) {
                        System.out.println("🔍 No products found.");
                    } else {
                        System.out.println("🔍 Matching Products:");
                        for (Product product : matchingProducts) {
                            System.out.println(product);
                        }
                    }
                    break;

                case "0":
                    System.out.println("Goodbye!");

                    // End the program with a success status (0).
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
