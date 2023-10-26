package com.example.business;
//import com.example.*;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private Set<Product> shoppingList;

    public Cart(){
        this.shoppingList = new HashSet<Product>();
    }

    public Set<Product> getShoppingCart() {
        return shoppingList;
    }

    public void setShoppingCart(Set<Product> shoppingList) {
        this.shoppingList = shoppingList;
    }

}
