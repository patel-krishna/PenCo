package com.example.business;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import com.example.business.Cart;
import java.util.HashMap;
import java.util.HashSet;
import java.sql.Statement;
import java.util.*;

public class Customer extends User {

    String passcode;

    private Cart shoppingCart;
    private Set<Order> orders = new HashSet<>();


    //Constructor
    public Customer(String passcode) {
        this.passcode = passcode;
        shoppingCart = new Cart(passcode);
    }

    //default constructor
    Customer() {
        this.passcode = passcode;
        shoppingCart = new Cart();
    }

    //Getters and setters
    public Cart getCart() {
        return new Cart(this.passcode);
    }

    public void setCart(Cart newCart) {
        this.shoppingCart = newCart;
    }

    public void setCart() {
        this.shoppingCart = new Cart(this.passcode);
    }

    public String getPasscode() {
        return this.passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public int getUserId() {
        int userId = shoppingCart.getUserIdByPasscode(this.passcode);
        return userId;
    }


    //business layer functions
    public void addProductToCart(String productSku, int quantity) {

        shoppingCart.getShoppingCart().put(productSku, quantity);

        // Check if a cart exists for the user in the db
        int cartId = shoppingCart.getCartIdByPasscode(this.passcode);
        if (cartId == -1) {
            // Create a new cart if it doesn't exist
            cartId = shoppingCart.createCart(this.passcode);
        }

        // Add the product to the cartItems table
        shoppingCart.addToCart(cartId, productSku, quantity);
    }


    public void removeProductFromCart(String productSku) {

        shoppingCart.getShoppingCart().remove(productSku);

        int cartId = shoppingCart.getCartIdByPasscode(this.passcode);
        if (cartId != -1) {
            // Cart exists so we must drop all rows with that id in cartItems
            shoppingCart.deleteCartItem(cartId,productSku);

        }
    }

    public void setProductQuantityInCart(String sku, int qty){
        shoppingCart.getShoppingCart().put(sku, qty);

        int cartId = shoppingCart.getCartIdByPasscode(this.passcode);
        if (cartId == -1) {
            //create a cart as it doesnt exist yet
            cartId = shoppingCart.createCart(this.passcode);
            shoppingCart.addToCart(cartId, sku, qty);
        }else{
            //there is already a cart check if the product is in the cart already
            if(shoppingCart.productExistsInCart(cartId, sku)){
                //update the row with new quantity
                if(qty>0){
                    shoppingCart.updateQuantityOfProduct(cartId,sku, qty);
                }

            }else {
                //add row to table with product
                shoppingCart.addToCart(cartId, sku, qty);
            }
        }
    }

    public void clearCart(){
        shoppingCart.getShoppingCart().clear();

        //get the cart from username
        int cartId = shoppingCart.getCartIdByPasscode(this.passcode);

        //if cart exists
        if(cartId != -1){
            shoppingCart.deleteAllCart(cartId);
        }
    }


    public List<Integer> getOrders(Customer user) {
        List<Integer> order_ids = new ArrayList<>();

        SQLConnector connector = new SQLConnector();

        int userId = user.getUserId();

        if (userId != -1) {
            // Make a database connection using your SQLConnector class
            try {
                String query = "SELECT * FROM Orders WHERE user_id = ?";

                try (PreparedStatement statement = connector.myDbConn.prepareStatement(query)) {
                    statement.setInt(1, userId);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int orderId = resultSet.getInt("order_id");
                            order_ids.add(orderId);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
            }
        }

        return order_ids;
    }

    public Order getOrder(User user, int orderId) {

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

            String getShippingQuery = "SELECT shipping_address FROM orders WHERE order_id=?";
            PreparedStatement shippingStatement = connector.myDbConn.prepareStatement(getShippingQuery);

            shippingStatement.setInt(1, orderId);
            ResultSet shippingAddressResult = shippingStatement.executeQuery();

            // Check if the result set has any rows
            if (shippingAddressResult.next()) {
                // Retrieve the shipping address
                String shippingAddress = shippingAddressResult.getString("shipping_address");

                // Do something with the shipping address, for example, print it
                System.out.println("Shipping Address: " + shippingAddress);

                Order order = new Order((Customer) user, shippingAddress, products);
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
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
    }



    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order); //how to link user
    }

    public int createOrder(Customer user,String shippingAddress) {
        HashMap<String, Integer> shoppingCart = this.getCart().getShoppingCart(this.passcode);
        if (shoppingCart.isEmpty()) {
            System.out.println("Cannot create an order because the shopping cart is empty.");
            return 0;
        }

        //create new order with inputted shipping address
        Order newOrder = new Order(user, shippingAddress,shoppingCart);
       // newOrder.setShoppingList(shoppingCart);

        // Insert order info get the generated orderId
        int orderId = newOrder.insertOrderInfo(this.getUserId(), shippingAddress);

        // Insert order items
        newOrder.insertOrderItems(orderId);

        //user.clearCart();

        // Add the new order to the customer's order history (you can do this if you have an orders collection)
        if (orders == null) {
            orders = new HashSet<Order>();
        }
        this.addOrder(newOrder);

        return orderId;
    }
}