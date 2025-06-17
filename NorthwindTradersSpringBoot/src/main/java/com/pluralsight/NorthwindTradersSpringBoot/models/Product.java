package com.pluralsight.NorthwindTradersSpringBoot.models;

public class Product {
    private int productId;
    private String name;
    private int categoryId;
    private double price;

    // This is a "constructor with parameters."
    // It lets us create a Product and set all its data at once.
    public Product(int productId, String name, int category, double price) {
        this.productId = productId;
        this.name = name;
        this.categoryId = category;
        this.price = price;
    }

    //default constructor with no parameters
    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price= $" + price
                ;
    }
}
