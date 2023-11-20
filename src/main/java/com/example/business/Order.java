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
    private HashMap<String, Integer> shoppingList; // Map of product SKU to quantity

    public Order(HashMap<String,Integer> products, String shippingAddress) {
        this.shippingAddress = shippingAddress;
        this.shoppingList = products;
    }

    //Constructor for Customer
    public Order(Customer customer, String shippingAddress, HashMap<String,Integer> products ) {
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.shoppingList = products;
    }



    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public HashMap<String, Integer> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(HashMap<String, Integer> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public int getOrderIdByUserId(Customer user) {
        int orderId = -1;
        int userId = user.getUserId();

        SQLConnector connector = new SQLConnector();

        try {
            String orderQuery = "SELECT order_id FROM Orders WHERE user_id = ?";
            PreparedStatement orderStatement = connector.myDbConn.prepareStatement(orderQuery);

            orderStatement.setInt(1, userId);

            try (ResultSet orderResult = orderStatement.executeQuery()) {
                if (orderResult.next()) {
                    orderId = orderResult.getInt("order_id");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            connector.closeConnection();
        }

        return orderId;
    }
    public int insertOrderInfo(int userId, String shippingAddress) {
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
                    return orderId;
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
        return orderId;
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

    public boolean isOrderShipped(int orderID) {
        SQLConnector connector = new SQLConnector();

        try {
            // Define the SQL select statement to check if the order has been shipped
            String selectOrderQuery = "SELECT COUNT(*) FROM ShippedOrders WHERE order_id = ?";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(selectOrderQuery);

            // Set the value for the orderID
            preparedStatement.setInt(1, orderID);

            // Execute the select
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any rows are returned (order has been shipped)
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Default to false if an exception occurs
        return false;
    }


}