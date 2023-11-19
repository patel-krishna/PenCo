package com.example.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GuestUser extends User {

    private Cart tempCart;
    private Order tempOrder;

    //DEFAULT CONSTRUCTOR
    public GuestUser(){
        tempCart = new Cart();
        tempOrder = null;
    }
    public Cart getTempCart() {
        return tempCart;
    }

    //GETTERS AND SETTERS
    public Order getTempOrder() {
        return tempOrder;
    }
    public void setTempCart(Cart tempCart) {
        this.tempCart = tempCart;
    }

    public void setTempOrder(Order tempOrder) {
        this.tempOrder = tempOrder;
    }


    //business layer functions
    public void addProductToCart(String productSku, int quantity) {
        tempCart.getShoppingCart().put(productSku, quantity);

    }


    public void removeProductFromCart(String productSku) {
        tempCart.getShoppingCart().remove(productSku);

    }

    public void setProductQuantityInCart(String sku, int qty){
        tempCart.getShoppingCart().put(sku, qty);
    }

    public void clearCart(){
        tempCart.getShoppingCart().clear();
    }


    public void createOrder(GuestUser user,String shippingAddress) {
        HashMap<String, Integer> shoppingCart = this.getTempCart().getShoppingCart();
        if (shoppingCart.isEmpty()) {
            System.out.println("Cannot create an order because the shopping cart is empty.");
            return;
        }

        //create new order with inputted shipping address
        Order newOrder = new Order(shoppingCart, shippingAddress);

        // Insert order info get the generated orderId
        int orderId = newOrder.insertOrderInfo(0, shippingAddress);

        // Insert order items
        newOrder.insertOrderItems(orderId);
    }
}
