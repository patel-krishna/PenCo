package com.example.business;
import com.example.business.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class User {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){
        username = null;
        password = null;
    }

    public Product getProduct(SQLConnector connector,String SKU){
        Product targetProduct = null;

        String query = "SELECT * FROM products WHERE SKU = ?";

        try{
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(query);
            // Set the SKU parameter in the prepared statement
            preparedStatement.setString(1, SKU);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                targetProduct = new Product();
                if (resultSet.next()) {
                    // Populate the targetProduct with data from the database
                    targetProduct.setSKU(resultSet.getString("SKU"));
                    targetProduct.setName(resultSet.getString("name"));
                    targetProduct.setDescription(resultSet.getString("description"));
                    targetProduct.setVendor(resultSet.getString("vendor"));
                    targetProduct.setImgSrc(resultSet.getString("imgSrc"));
                    targetProduct.setPrice(resultSet.getDouble("price"));
                    targetProduct.setURL(resultSet.getString("url_slug"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Object not found");
            // Handle any exceptions (e.g., database connection or query errors)
        }
        return targetProduct;
    }

    public Product getProductBySlug(SQLConnector connector, String URL){

        Product targetProduct = null;

        String query = "SELECT * FROM products WHERE url_slug = ?";

        try{
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(query);
            // Set the SKU parameter in the prepared statement
            preparedStatement.setString(1, URL);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                targetProduct = new Product();
                if (resultSet.next()) {
                    // Populate the targetProduct with data from the database
                    targetProduct.setSKU(resultSet.getString("SKU"));
                    targetProduct.setName(resultSet.getString("name"));
                    targetProduct.setDescription(resultSet.getString("description"));
                    targetProduct.setVendor(resultSet.getString("vendor"));
                    targetProduct.setImgSrc(resultSet.getString("imgSrc"));
                    targetProduct.setPrice(resultSet.getDouble("price"));
                    targetProduct.setURL(resultSet.getString("url_slug"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Object not found");
            // Handle any exceptions (e.g., database connection or query errors)
        }
        return targetProduct;

    }
}
