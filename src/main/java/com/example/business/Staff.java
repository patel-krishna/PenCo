package com.example.business;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.sql.*;
import java.util.List;

public class Staff extends User{

    private String passcode;

    public Staff(String passcode){
        this.passcode = passcode;
    }

    public String getPasscode() {
        return this.passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public int getUserId() {
        int userId = Cart.getUserIdByPasscode(this.passcode);
        return userId;
    }

    public void createProduct(String sku, String name){
        SQLConnector connector = new SQLConnector();
        try {
            // Define the SQL insert statement
            String insertQuery = "INSERT INTO products (SKU, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(insertQuery);

            // Set the values for the new product
            preparedStatement.setString(1, sku);
            preparedStatement.setString(2, name);

            // Execute the insert
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Product created successfully.");
            } else {
                System.out.println("Product creation failed.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
    }

    /**
     * If the attribute is not to be updated, set it to an empty string or null
     * @param updatedProd
     * @param name
     * @param description
     * @param URL
     * @param SKU
     * @param price
     * @param imgSrc
     */
    public void updateProduct(Product updatedProd,String name, String description, String vendor, String URL, String SKU, double price, String imgSrc){
        if(!name.isBlank()){
            updatedProd.setName(name);
        }
        if(!description.isBlank()){
            updatedProd.setDescription(description);
        }
        if(!vendor.isBlank()){
            updatedProd.setVendor(vendor);
        }
        if(!URL.isBlank()){
            updatedProd.setURL(URL);
        }
        if(!SKU.isBlank()){
            updatedProd.setSKU(SKU);
        }
        if(price != 0){
            updatedProd.setPrice(price);
        }
        if(!imgSrc.isBlank()){
            updatedProd.setImgSrc(imgSrc);
        }

        SQLConnector connector = new SQLConnector();

        try {
            // Define the SQL update statement
            String updateQuery = "UPDATE products SET name = ?, description = ?, vendor = ?, url_slug = ?, price = ?, imgSrc = ? WHERE SKU = ? ";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(updateQuery);

            // Set the values for the product
            preparedStatement.setString(1, updatedProd.getName());
            preparedStatement.setString(2, updatedProd.getDescription());
            preparedStatement.setString(3, updatedProd.getVendor());
            preparedStatement.setString(4, updatedProd.getURL());
            preparedStatement.setDouble(5, updatedProd.getPrice());
            preparedStatement.setString(6, updatedProd.getImgSrc());
            preparedStatement.setString(7, updatedProd.getSKU());

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product update failed. No matching SKU found.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
        connector.closeConnection();
    }

    /**
     * TO-DO
     * @return
     */

    public File downloadProductCatalog() {
        SQLConnector connector = new SQLConnector();


        // Specify the CSV file path relative to the web application's context
        File csvFile = new File("product_catalog.csv");

        if(csvFile.exists()){
            csvFile.delete();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            String productsQuery = "SELECT * FROM Products";
            try (Connection connection = connector.myDbConn;
                 PreparedStatement statement = connection.prepareStatement(productsQuery);
                 ResultSet resultSet = statement.executeQuery()) {

                // Write the CSV header
                writer.write("SKU,ProductName,Description,Vendor,URL,Price,ImgSRC");
                writer.newLine();

                // Iterate through the result set and write each row to the CSV file
                while (resultSet.next()) {
                    String sku = resultSet.getString("SKU");
                    String productName = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String vendor = resultSet.getString("vendor");
                    String url = resultSet.getString("url_slug");
                    double price = resultSet.getDouble("price");
                    String img = resultSet.getString("imgSrc");

                    String csvRow = String.join(",", sku, productName, description, vendor, url, Double.toString(price), img);
                    writer.write(csvRow);
                    writer.newLine();
                }

                System.out.println("Data exported to " + csvFile.getAbsolutePath());
            } catch (SQLException e) {
                throw new RuntimeException("SQL Error: " + e.getMessage(), e);
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.getMessage(), e);
        }

        connector.closeConnection();
        return csvFile;
    }

    public List<Order> GetOrders(Customer user) {
        List<Order> orders = new ArrayList<>();

        SQLConnector connector = new SQLConnector();

        int userId = user.getUserId();

        if (userId != -1) {
            // Make a database connection using your SQLConnector class
            try {
                String query = "SELECT * FROM Orders";

                try (PreparedStatement statement = connector.myDbConn.prepareStatement(query)) {
                    statement.setInt(1, userId);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int orderId = resultSet.getInt("order_id");
                            String shippingAddress = resultSet.getString("shipping_address");

                            // Create an Order object and add it to the list
                            //Order order = new Order(user, shippingAddress);
                            //orders.add(order);

                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
            }
        }

        return orders;
    }



    //OLD FUNCTION FROM ASSIGNMENT 1
        public static void writeProductsToXML(HashMap<String, Product> productMap, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            if(!productMap.isEmpty()){
                writer.newLine();
                writer.write("<products>");
                writer.newLine();

                for (String productId : productMap.keySet()) {
                    Product product = productMap.get(productId);

                    writer.write("  <product>");
                    writer.newLine();
                    writer.write("    <name>" + product.getName() + "</name>");
                    writer.newLine();
                    writer.write("    <description>" + product.getDescription() + "</description>");
                    writer.newLine();
                    writer.write("    <vendor>" + product.getVendor() + "</vendor>");
                    writer.newLine();
                    writer.write("    <slug>" + product.getURL() + "</slug>");
                    writer.newLine();
                    writer.write("    <sku>" + product.getSKU() + "</sku>");
                    writer.newLine();
                    writer.write("    <Price>" + product.getPrice() + "</Price>");
                    writer.newLine();
                    writer.write("    <img>" + product.getImgSrc() + "</img>");
                    writer.newLine();
                    writer.write("  </product>");
                    writer.newLine();
                }
            }
            writer.write("</products>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Integer> getOrders() {
        List<Integer> order_ids = new ArrayList<>();

        SQLConnector connector = new SQLConnector();

            // Make a database connection using your SQLConnector class
            try {
                String query = "SELECT * FROM Orders WHERE user_id IS NOT NULL;";

                try (PreparedStatement statement = connector.myDbConn.prepareStatement(query)) {

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int orderId = resultSet.getInt("order_id");
                           // String shippingAddress = resultSet.getString("shipping_address");

                            // Create an Order object and add it to the list
                           // Order order = new Order(user, shippingAddress);
                            order_ids.add(orderId);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
        connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
    }

        return order_ids;
    }
    public Order getOrder(int orderId) {

        //from order id get all order items --> get the product_sku and quantiy
            //add this information into a temporary hashmap
        //from order id also get shipping address

        HashMap<String, Integer> products = new HashMap<>();
        SQLConnector connector = new SQLConnector();

        // Make a database connection using your SQLConnector class
        try {
            // Define the SQL insert statement
            String getProductsQuery = "SELECT product_sku,quantity FROM orderitems WHERE order_id=?";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(getProductsQuery);

            // Set the values for object
            preparedStatement.setInt(1, orderId);

            // Execute the insert
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String productSku = resultSet.getString("product_sku");
                int quantity = resultSet.getInt("quantity");

                // Put the values into the HashMap
                products.put(productSku, quantity);
            }

            String getShippingQuery = "SELECT (shipping_address) FROM orders WHERE order_id=?";
            PreparedStatement shippingStatement = connector.myDbConn.prepareStatement(getShippingQuery);

            shippingStatement.setInt(1, orderId);
            ResultSet shippingAddressResult = shippingStatement.executeQuery();

            // Check if the result set has any rows
            if (shippingAddressResult.next()) {
                // Retrieve the shipping address
                String shippingAddress = shippingAddressResult.getString("shipping_address");

                // Do something with the shipping address, for example, print it
                System.out.println("Shipping Address: " + shippingAddress);

                Order order = new Order(products,shippingAddress);
                return order;
                // Do something with the order
            } else {
                // Handle the case where no shipping address was found
                System.out.println("No shipping address found for order ID: " + orderId);
            }


            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            connector.closeConnection();
        }
    }

    public void shipOrder(int orderID, int trackingNumber) {
        SQLConnector connector = new SQLConnector();
        try {
            // Define the SQL insert statement
            String insertOrderQuery = "INSERT INTO ShippedOrders (order_id, tracking_number) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(insertOrderQuery)) {
                // Set the values for object
                preparedStatement.setInt(1, orderID);
                preparedStatement.setInt(2, trackingNumber);

                // Execute the insert
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
    }

    public void changePermission(User changedUser, String role) {

        int isStaff;

        switch(role.toLowerCase()){
            case "customer":
                isStaff = 0;
                break;

            case "staff":
                isStaff = 1;
                break;

            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        SQLConnector connector = new SQLConnector();

        int user_id;

        if(changedUser instanceof Customer){
            Customer customer = (Customer) changedUser;
            user_id = customer.getUserId();
        }else if(changedUser instanceof Staff){
            Staff staff = (Staff) changedUser;
            user_id = staff.getUserId();
        }else{
            System.out.println("User does not exist!");
            return;
        }

        try {
            // Define the SQL update statement
            String updateQuery = "UPDATE users SET isStaff = ? WHERE user_id = ? ";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(updateQuery);

            // Set the values for the product
            preparedStatement.setInt(1, isStaff);
            preparedStatement.setInt(2, user_id);

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User update failed. No matching id found.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
        connector.closeConnection();
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<>();
        SQLConnector connector = new SQLConnector();

        try{

            Statement statement = connector.myDbConn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                int userID = resultSet.getInt("user_id");
                String passcode = resultSet.getString("passcode");
                int isStaff = resultSet.getInt("isStaff");

                if(isStaff == 0){
                    Customer customer = new Customer(passcode);
                    userList.add(customer);
                }
                if(isStaff == 1){
                    Staff staff = new Staff(passcode);
                    userList.add(staff);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
        return userList;
    }

}
