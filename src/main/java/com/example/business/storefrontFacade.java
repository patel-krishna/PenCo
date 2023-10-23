package com.example.business;

import java.util.HashMap;
import java.io.File;

public class storefrontFacade {
    public static HashMap<String,Product> allProductsSku;

    final String filePathProducts = "C:\\Users\\Krish\\Documents\\School\\Concordia\\Projects\\PenCo\\src\\main\\resources\\products.xml";
    final String getFilePathUsers = "C:\\Users\\Krish\\Documents\\School\\Concordia\\Projects\\PenCo\\src\\main\\resources\\users.xml";

    static HashMap<String, User> allUsers;
    static User currentUser;

    public void createProduct(Staff staff, String sku, String name) {
        // You may add authorization checks here to ensure staff privileges.
        staff.createProduct(allProductsSku, sku, name);
    }
    public void updateProduct(Product updatedProd, String name, String description, String vendor, String URL, String SKU, double price, String imgSrc){
        if (currentUser instanceof Staff) {
            Staff staffUser = (Staff) currentUser;
            staffUser.updateProduct(updatedProd, name, description, vendor, URL, SKU, price, imgSrc);
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
        if (currentUser instanceof Staff) {
            System.out.println("The user is a staff and does not have a cart");
            return null;

        } else {
            Customer customer = (Customer) currentUser;
            return customer.getCart();
        }
    }

    public void addProductToCart(String sku){
        Product productToAdd = allProductsSku.get(sku);
        if(currentUser instanceof Customer){
            Customer customer = (Customer) currentUser;
            customer.addProductToCart(productToAdd);
        }
    }

    public void removeProductFromCart(String sku){
        Product productToRemove = allProductsSku.get(sku);
        if(currentUser instanceof Customer){
            Customer customer = (Customer) currentUser;
            customer.removeProductFromCart(productToRemove);
        }
    }

    public File downloadProductCatalogue(){
        if(currentUser instanceof Staff){
            Staff staff = (Staff) currentUser;
            staff.downloadProductCatalog(allProductsSku, filePathProducts);
        }else{
            System.out.println("Not a staff, cannot download catalogue.");
            return null;
        }
            return null;
    }

}


