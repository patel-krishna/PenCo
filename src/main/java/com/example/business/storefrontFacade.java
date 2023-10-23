package com.example.business;

import java.util.HashMap;

public class storefrontFacade {
    public static HashMap<String,Product> allProductsSku;
    static HashMap<String, User> allUsers;
    static User currentUser;

    public void createProduct(Staff staff, String sku, String name) {
        // You may add authorization checks here to ensure staff privileges.
        staff.createProduct(sku, name);
    }
    public void updateProduct(Product updatedProd, String name, String description, String URL, String SKU, double price, String imgSrc){
        if (currentUser instanceof Staff) {
            Staff staffUser = (Staff) currentUser;
            staffUser.updateProduct(updatedProd, name, description, URL, SKU, price, imgSrc);
        } else {
            // Handle the case where currentUser is not a Staff
            // You could throw an exception or handle it in some other way
            System.out.println("The user is not a staff and cannot update product inventory");

        }
    }

    public Product getProduct(String sku){
       return currentUser.getProduct(allProductsSku, sku);
    }

    public Product getProductBySlug(String slug){
        return currentUser.getProductBySlug(allProductsSku,slug);
    }

    public Cart getCart() {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getCart();
        } else {
            // Handle the case where currentUser is not a Staff
            // You could throw an exception or handle it in some other way
            System.out.println("The user is a staff and does not have a cart");
            return null;
        }
    }
    
}


