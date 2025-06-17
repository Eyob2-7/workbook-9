package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    // This is the DataSource that we will use to connect to the database.
    // The DataSource is created in our DbConfiguration class.

    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        String sql = """
                INSERT INTO Products
                (ProductID,ProductName,CategoryID,UnitPrice)
                VALUES
                (?,?,?,?)
                """;
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getCategoryId());
            preparedStatement.setDouble(4, product.getPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = """
                SELECT *
                FROM Products
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("ProductName");
                int categoryId = resultSet.getInt("CategoryID");
                double price = resultSet.getDouble("UnitPrice");

                Product product = new Product(id, name, categoryId, price);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void deleteById(int productId) {

        String sql = """
                DELETE
                FROM products
                WHERE ProductID = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {

        String sql = """ 
                UPDATE products
                SET
                productName = ?
                ,CategoryID = ?
                ,UnitPrice = ?
                WHERE ProductID = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getProductId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchByName(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = """
                SELECT *
                FROM products
                WHERE ProductName LIKE ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int category = rs.getInt("CategoryID");
                double price = rs.getDouble("UnitPrice");

                products.add(new Product(id, name, category, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
