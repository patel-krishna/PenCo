package com.example.business;

import java.util.HashMap;
import java.util.HashSet;

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

}
