package com.example.business;

public class Staff extends User{

    public Staff(String username, String password){
        super(username, password);
    }

    public void createProduct(String sku, String name){
        Product temp = new Product(name, "", "", sku, 0, "");
        globalVariables.allProductsSlug.put(sku, temp);
    }

}
