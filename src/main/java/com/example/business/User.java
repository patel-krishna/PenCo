package com.example.business;
import com.example.business.Product;

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
    public static Product getProduct(String SKU){
        return globalVariables.allProductsSku.get(SKU);
    }
    public static Product getProductBySlug(String URL){

        for (String key : globalVariables.allProductsSku.keySet()) {
            Product value = globalVariables.allProductsSku.get(key);
            if(value.URL.equals(URL)){
                return value;
            }
        }
        return null;

    }
}
