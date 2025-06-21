package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    @Autowired
    private DataSource dataSource;

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
    public Product getById(int productId) {
        String sql = """
                SELECT *
                FROM Products
                WHERE ProductID = ?
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ProductID");
                    String name = resultSet.getString("ProductName");
                    int categoryId = resultSet.getInt("CategoryID");
                    double price = resultSet.getDouble("UnitPrice");
                    return new Product(id, name, categoryId, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product add(Product product) {
        // This is the SQL INSERT statement we will run.
        // We are inserting the Product Name, Category ID, and Unit Price.
        String sql = "INSERT INTO Products (ProductName, CategoryID, UnitPrice) VALUES (?, ?, ?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the first parameter (?) to the products name.
            stmt.setString(1, product.getProductName());

            // Set the second parameter (?) to the products category id.
            stmt.setInt(2, product.getCategoryId());

            // Set the third parameter (?) to the unit price.
            stmt.setDouble(3, product.getUnitPrice());

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

            // Retrieve the generated film_id
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    product.setProductId(newId); // Set the generated ID on the Film object
                }
            }


        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        return product;
    }


    @Override
    public void deleteById(int id) {

        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateById(int id, Product product) {

        String sql =
                """
                UPDATE
                Products
                SET
                ProductName = COALESCE(?, ProductName),
                CategoryID = COALESCE(?, CategoryID),
                UnitPrice = COALESCE(?, UnitPrice)
                WHERE
                ProductID = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());

            if (product.getCategoryId() == 0) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, product.getCategoryId());
            }

            if (product.getUnitPrice() == 0.0) {
                stmt.setNull(3, Types.DOUBLE);
            } else {
                stmt.setDouble(3, product.getUnitPrice());
            }

            stmt.setInt(4, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
