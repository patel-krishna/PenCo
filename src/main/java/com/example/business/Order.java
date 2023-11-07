package com.example.business;

import java.util.HashMap;

public class Order {
    int orderID;
    String shippingAddress;
    int trackNumber;
    private HashMap<String, Integer> shoppingList;

    public Order(int orderID, String shippingAddress) {
        this.orderID = orderID;
        this.shippingAddress = shippingAddress;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
