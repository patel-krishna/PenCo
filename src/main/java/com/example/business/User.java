package com.example.business;
import com.example.business.Product;

import java.util.HashMap;

public class User {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){
        username = null;
        password = null;
    }

    public Product getProduct(HashMap<String, Product> allProducts, String SKU){
        return allProducts.get(SKU);
    }
    public Product getProductBySlug(HashMap<String, Product> allProducts, String URL){

        for (String key : allProducts.keySet()) {
            Product value = allProducts.get(key);
            if(value.URL.equals(URL)){
                return value;
            }
        }
        return null;

    }
}
