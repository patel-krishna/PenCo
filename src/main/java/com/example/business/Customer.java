package com.example.business;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import com.example.business.Cart;
import java.util.HashMap;
import java.util.HashSet;
import java.sql.Statement;

public class Customer extends User {

    private Cart shoppingCart;
    private Set<Order> orders;


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
    public int getOrderId() {
        int orderId = -1;

        SQLConnector connector = new SQLConnector();

        try {
            String query = "SELECT MAX(order_id) FROM orders";

            try (Statement statement = connector.myDbConn.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    orderId = resultSet.getInt(1);
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void createOrder(Customer user,String shippingAddress) {
        HashMap<String, Integer> shoppingCart = this.getCart().getShoppingCart();
        if (shoppingCart.isEmpty()) {
            System.out.println("Cannot create an order because the shopping cart is empty.");
        }

        //create new order with inputted shipping address
        Order newOrder = new Order(this, shippingAddress);
        newOrder.setShoppingList(shoppingCart);


        // Insert order items & order info into the database
        newOrder.insertOrderInfo(this.getUserId(), shippingAddress);
        newOrder.insertOrderItems();

        this.clearCart();

        // Add the new order to the customer's order history (you can do this if you have an orders collection)
        if (orders == null) {
            orders = new HashSet<Order>();
        }
        orders.add(newOrder);
    }
}