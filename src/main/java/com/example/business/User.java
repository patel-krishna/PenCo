package com.example.business;
import com.example.business.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    public Product getProduct(String SKU){
        SQLConnector connector = new SQLConnector();
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
        }finally {
        connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
        return targetProduct;
    }

    public Product getProductBySlug(String URL){
        SQLConnector connector = new SQLConnector();
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
        }finally {
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
        return targetProduct;

    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> productList = new ArrayList<>();
        SQLConnector connector = new SQLConnector();

        try{

            Statement statement = connector.myDbConn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");

            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String SKU = resultSet.getString("SKU");
                String URL = resultSet.getString("url_slug");
                String vendor = resultSet.getString("vendor");
                String imgSrc = resultSet.getString("imgSrc");

                Product product = new Product(productName, description, vendor, URL, SKU, price, imgSrc);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
        return productList;
    }

    public int getOrderId(int userId, String shippingAddress) {
        int orderId = -1;

        SQLConnector connector = new SQLConnector();

        try {
            String query = "SELECT MAX(order_id) FROM orders WHERE user_id=? AND shipping_address=?";

            try (PreparedStatement statement = connector.myDbConn.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setString(2, shippingAddress);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        orderId = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            connector.closeConnection();
        }

        return orderId;
    }

    public int getTrackingNumber(int orderID) {
        SQLConnector connector = new SQLConnector();
        int trackingNumber = -1; // Default value if not found

        try {
            // Define the SQL select statement
            String selectOrderQuery = "SELECT tracking_number FROM ShippedOrders WHERE order_id = ?";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(selectOrderQuery);

            // Set the value for the order ID
            preparedStatement.setInt(1, orderID);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a row is found
            if (resultSet.next()) {
                // Retrieve the tracking number from the result set
                trackingNumber = resultSet.getInt("tracking_number");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            // Close the database connection (if needed)
            connector.closeConnection();
        }

        return trackingNumber;
    }
    public void setPasscode(User user, String passcode){
        if (passcode.length() < 5 || !passcode.matches("^[a-zA-Z0-9]*$")) {
            System.out.println("Passcode must be at least 4 characters long and alphanumeric.");
            return;
        }
        SQLConnector connector = new SQLConnector();

        try {
            String checkPasscode = "SELECT passcode FROM Users WHERE passcode = ? ";
            PreparedStatement checkPasscodeStatement = connector.myDbConn.prepareStatement(checkPasscode);
            checkPasscodeStatement.setString(1, passcode);

            ResultSet conflictingUsers = checkPasscodeStatement.executeQuery();
            boolean isDuplicatePasscode = conflictingUsers.next();
            if(isDuplicatePasscode) {
                throw new Exception("Passcode is already in use, please pick another one.");
            }

            String setPasscode = "UPDATE Users SET passcode = ? WHERE user_id = ?";
            PreparedStatement setPasscodeStatement = connector.myDbConn.prepareStatement(setPasscode);
            setPasscodeStatement.setString(1, passcode);
            if (user instanceof Customer){
                Customer customer =(Customer) user;
                setPasscodeStatement.setInt(2, customer.getUserId());
            }
            else if (user instanceof Staff){
                Staff staff =(Staff) user;
                setPasscodeStatement.setInt(2, staff.getUserId());
            }
            else {
                throw new Exception("User must be an instance of customer or staff");
            }

            setPasscodeStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connector.closeConnection();
        }
    }
}