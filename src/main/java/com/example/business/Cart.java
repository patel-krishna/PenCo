package com.example.business;
//import com.example.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private HashMap<Product, Integer> shoppingList;

    public Cart(){
        this.shoppingList = new HashMap<Product, Integer>();
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingList;
    }

    public void setShoppingCart(HashMap<Product,Integer> shoppingList) {
        this.shoppingList = shoppingList;
    }

}
