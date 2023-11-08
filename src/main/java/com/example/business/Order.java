package com.example.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Order {
    private int orderId;
    private Customer customer;
    private String shippingAddress;
    private int trackNumber;
    private HashMap<String, Integer> shoppingList; // Map of product SKU to quantity

    public Order(Customer customer, String shippingAddress) {
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.shoppingList = new HashMap<>();
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public HashMap<String, Integer> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(HashMap<String, Integer> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void insertOrderInfo(int userId, String shippingAddress) {
        SQLConnector connector = new SQLConnector();
        int orderId = -1; // Initialize orderId to -1

        try {
            // Define the SQL query to insert order details into Orders and retrieve the generated order ID
            String insertOrderQuery = "INSERT INTO Orders (user_id, shipping_address) VALUES (?, ?)";
            PreparedStatement insertOrderStatement = connector.myDbConn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
            insertOrderStatement.setInt(1, userId);
            insertOrderStatement.setString(2, shippingAddress);

            // Execute the insertOrder query and retrieve the generated keys
            int affectedRows = insertOrderStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1); // Get the generated order ID
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    public void insertOrderItems(int orderId) {
        SQLConnector connector = new SQLConnector();

        try {
            String insertOrderItemQuery = "INSERT INTO OrderItems (order_id, product_sku, quantity) VALUES (?, ?, ?)";
            PreparedStatement insertOrderItemStatement = connector.myDbConn.prepareStatement(insertOrderItemQuery);

            for (HashMap.Entry<String, Integer> entry : shoppingList.entrySet()) {
                String productSku = entry.getKey();
                int quantity = entry.getValue();

                insertOrderItemStatement.setInt(1, orderId); // Use the retrieved orderId
                insertOrderItemStatement.setString(2, productSku);
                insertOrderItemStatement.setInt(3, quantity);

                // Execute the insertOrderItem query to insert each item
                insertOrderItemStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }


}
