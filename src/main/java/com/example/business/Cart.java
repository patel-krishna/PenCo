package com.example.business;
//import com.example.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.sql.*;

public class Cart {
    private HashMap<String, Integer> shoppingList;

    public Cart(){
        this.shoppingList = new HashMap<String, Integer>();
    }

    public Cart(String username){
        this.shoppingList = getShoppingCart(username);
    }

    public HashMap<String, Integer> getShoppingCart() {
        return shoppingList;
    }

    public HashMap<String, Integer> getShoppingCart(String username) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        SQLConnector connector = new SQLConnector();
        int userId = -1;

        try{

            String userQuery = "SELECT user_id FROM Users WHERE username = ?";
            PreparedStatement userStatement = connector.myDbConn.prepareStatement(userQuery);

            userStatement.setString(1, username);

            try (ResultSet userResult = userStatement.executeQuery()) {
                if (userResult.next()) {
                    userId = userResult.getInt("user_id");
                }
            }

            String cartQuery = "SELECT product_sku, quantity FROM CartItems WHERE cart_id IN " +
                    "(SELECT cart_id FROM Carts WHERE user_id = ?)";

            try (PreparedStatement cartStatement = connector.myDbConn.prepareStatement(cartQuery)) {
                // Set the user ID parameter in the cart query
                cartStatement.setInt(1, userId);

                try (ResultSet cartResultSet = cartStatement.executeQuery()) {
                    while (cartResultSet.next()) {
                        String productSKU = cartResultSet.getString("product_sku");
                        int quantity = cartResultSet.getInt("quantity");

                        // Add the product SKU and quantity to the cartItems map
                        map.put(productSKU, quantity);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    public void setShoppingCart(HashMap<String,Integer> shoppingList) {
        this.shoppingList = shoppingList;
    }


    public int getCartIdByUsername(String username) {
        // Implement the logic to retrieve the cart ID for the given username
        // You can use a SQL query to fetch the cart ID based on the user's username
        // Return the cart ID if found, or -1 if not found
        // Make sure to handle database operations properly

        int userId = -1;
        int cartId = -1;

        SQLConnector connector = new SQLConnector();

        try {
            String userQuery = "SELECT user_id FROM Users WHERE username = ?";
            PreparedStatement userStatement = connector.myDbConn.prepareStatement(userQuery);

            userStatement.setString(1, username);

            try (ResultSet userResult = userStatement.executeQuery()) {
                if (userResult.next()) {
                    userId = userResult.getInt("user_id");
                }
            }

            String cartQuery = "SELECT cart_id FROM carts WHERE user_id =?";
            PreparedStatement cartStatement = connector.myDbConn.prepareStatement(cartQuery);

            cartStatement.setInt(1, userId);
            try (ResultSet cartResult = cartStatement.executeQuery()) {
                if (cartResult.next()) {
                    cartId = cartResult.getInt("cart_id");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return cartId;
    }

    public int createCart(String username) {
        // Implement the logic to create a new cart and return its cart ID
        // You can use a SQL query to insert a new cart with the user's user_id
        // Return the generated cart ID if the cart is successfully created
        // Make sure to handle database operations properly

            int userId = getUserIdByUsername(username);

            if (userId != -1) {
                SQLConnector connector = new SQLConnector();

                // Define the SQL query to insert a new cart and get the generated cart_id
                String insertCartQuery = "INSERT INTO Carts (user_id) VALUES (?)";

                try{
                     PreparedStatement insertCartStatement = connector.myDbConn.prepareStatement(insertCartQuery, Statement.RETURN_GENERATED_KEYS);

                    insertCartStatement.setInt(1, userId);

                    // Execute the insert statement and get the generated keys
                    int affectedRows = insertCartStatement.executeUpdate();

                    if (affectedRows > 0) {
                        ResultSet generatedKeys = insertCartStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            return generatedKeys.getInt(1); // Return the generated cart_id
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle any exceptions (e.g., database connection or query errors)
                }
            }

            return -1;
    }

    private int getUserIdByUsername(String username) {
        int userId = -1;

        SQLConnector connector = new SQLConnector();

        try {
            String userQuery = "SELECT user_id FROM Users WHERE username = ?";
            PreparedStatement userStatement = connector.myDbConn.prepareStatement(userQuery);

            userStatement.setString(1, username);

            try (ResultSet userResult = userStatement.executeQuery()) {
                if (userResult.next()) {
                    userId = userResult.getInt("user_id");
                    return userId;
                }
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userId;
    }

    public void addToCart(int cartId, String productSKU, int quantity) {
        // Implement the logic to add a product to the cartItems table
        // You can use a SQL query to insert the product into cartItems with the provided cart_id, product_sku, and quantity
        // Make sure to handle database operations properly

        SQLConnector connector = new SQLConnector();

        String insertCartItemQuery = "INSERT INTO CartItems (cart_id, product_sku, quantity) VALUES (?, ?, ?)";

        try {
             PreparedStatement insertCartItemStatement = connector.myDbConn.prepareStatement(insertCartItemQuery);

            insertCartItemStatement.setInt(1, cartId);
            insertCartItemStatement.setString(2, productSKU);
            insertCartItemStatement.setInt(3, quantity);

            // Execute the insert statement
            insertCartItemStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions (e.g., database connection or query errors)
        }

    }

    public void deleteCartItem(int cartId, String sku) {
        SQLConnector connector = new SQLConnector();
        // Define the SQL query to delete items with a specific cart_id from CartItems
        String deleteProductQuery = "DELETE FROM CartItems WHERE cart_id = ? AND product_sku = ?";

        try {
             PreparedStatement deleteCartItemsStatement = connector.myDbConn.prepareStatement(deleteProductQuery);

            deleteCartItemsStatement.setInt(1, cartId);
            deleteCartItemsStatement.setString(2, sku);

            // Execute the delete statement
            deleteCartItemsStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions (e.g., database connection or query errors)
        }
    }


}
