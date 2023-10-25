package com.example.business;
import java.util.Set;
import com.example.business.Cart;

public class Customer extends User {

    private Cart shoppingCart;

    //Constructor
    Customer(String username, String password) {
        super(username, password);
        shoppingCart = new Cart();
    }

    //default constructor
    Customer() {
        super(null, null);
        shoppingCart = new Cart();
    }

    //Getters and setters
    public Cart getCart() {
        return this.shoppingCart;
    }

    public void setCart(Cart newCart) {
        this.shoppingCart = newCart;
    }

    //business layer functions
    public void addProductToCart(Product newProd) {
        shoppingCart.getShoppingCart().add(newProd);
    }

    public void removeProductFromCart(Product removeProd) {
        shoppingCart.getShoppingCart().remove(removeProd);
    }

}