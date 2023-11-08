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
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public void insertOrderItem(int orderId, String productSku, int quantity, int userId, String shippingAddress) {
        // Establish a database connection
        SQLConnector connector = new SQLConnector();

        // Define the SQL query to insert order details into Orders
        String insertOrderQuery = "INSERT INTO Orders (user_id, shipping_address) VALUES (?, ?)";

        // Define the SQL query to insert an order item into OrderItems
        String insertOrderItemQuery = "INSERT INTO OrderItems (order_id, product_sku, quantity) VALUES (?, ?, ?)";


        try (PreparedStatement insertOrderItemStatement = connector.myDbConn.prepareStatement(insertOrderItemQuery);
             PreparedStatement insertOrderStatement = connector.myDbConn.prepareStatement(insertOrderQuery)) {

            insertOrderStatement.setInt(1, userId);
            insertOrderStatement.setString(2, shippingAddress);

            // Execute the insertOrderItem query to insert order details
            insertOrderItemStatement.executeUpdate();

            insertOrderItemStatement.setInt(1, orderId);
            insertOrderItemStatement.setString(2, productSku);
            insertOrderItemStatement.setInt(3, quantity);

            // Execute the insertOrder query to insert each item
            insertOrderStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

}
