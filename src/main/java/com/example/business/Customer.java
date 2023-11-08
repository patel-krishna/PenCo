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

    private Cart shoppingCart;
    private Set<Order> orders = new HashSet<>();


    //Constructor
    public Customer(String username, String password) {
        super(username, password);
        shoppingCart = new Cart(username);
    }

    //default constructor
    Customer() {
        super(null, null);
        shoppingCart = new Cart();
    }

    //Getters and setters
    public Cart getCart() {
        return new Cart(this.username);
    }

    public void setCart(Cart newCart) {
        this.shoppingCart = newCart;
    }

    public void setCart() {
        this.shoppingCart = new Cart(this.username);
    }

    public int getUserId() {
        int userId = shoppingCart.getUserIdByUsername(this.username);
        return userId;
    }


    //business layer functions
    public void addProductToCart(String productSku, int quantity) {

        shoppingCart.getShoppingCart().put(productSku, quantity);

        // Check if a cart exists for the user in the db
        int cartId = shoppingCart.getCartIdByUsername(this.username);
        if (cartId == -1) {
            // Create a new cart if it doesn't exist
            cartId = shoppingCart.createCart(this.username);
        }

        // Add the product to the cartItems table
        shoppingCart.addToCart(cartId, productSku, quantity);
    }


    public void removeProductFromCart(String productSku) {

        shoppingCart.getShoppingCart().remove(productSku);

        int cartId = shoppingCart.getCartIdByUsername(this.username);
        if (cartId != -1) {
            // Cart exists so we must drop all rows with that id in cartItems
            shoppingCart.deleteCartItem(cartId,productSku);

        }
    }

    public void setProductQuantityInCart(String sku, int qty){
        shoppingCart.getShoppingCart().put(sku, qty);

        int cartId = shoppingCart.getCartIdByUsername(this.username);
        if (cartId == -1) {
            //create a cart as it doesnt exist yet
            cartId = shoppingCart.createCart(username);
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
        int cartId = shoppingCart.getCartIdByUsername(this.username);

        //if cart exists
        if(cartId != -1){
            shoppingCart.deleteAllCart(cartId);
        }
    }

    public List<Order> GetOrders(Customer user) {
        List<Order> orders = new ArrayList<>();

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
                            String shippingAddress = resultSet.getString("shipping_address");

                            // Create an Order object and add it to the list
                            Order order = new Order(user, shippingAddress);
                            orders.add(order);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order); //how to link user
    }

    public void createOrder(Customer user,String shippingAddress) {
        HashMap<String, Integer> shoppingCart = this.getCart().getShoppingCart(user.getUsername());
        if (shoppingCart.isEmpty()) {
            System.out.println("Cannot create an order because the shopping cart is empty.");
            return;
        }

        //create new order with inputted shipping address
        Order newOrder = new Order(this, shippingAddress);
        newOrder.setShoppingList(shoppingCart);

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
    }
}