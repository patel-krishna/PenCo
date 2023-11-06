package com.example.business;
import java.util.Set;
import com.example.business.Cart;

public class Customer extends User {

    private Cart shoppingCart;

    //Constructor
    Customer(String username, String password) {
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

    //business layer functions
    public void addProductToCart(String productSku, int quantity) {

        shoppingCart.getShoppingCart().put(productSku, quantity);

        // Check if a cart exists for the user in the db
        int cartId = shoppingCart.getCartIdByUsername(username);
        if (cartId == -1) {
            // Create a new cart if it doesn't exist
            cartId = shoppingCart.createCart(username);
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

}