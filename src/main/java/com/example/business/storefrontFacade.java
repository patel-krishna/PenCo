package com.example.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

public class storefrontFacade {

//    public SQLConnector connector;
//
//    public storefrontFacade() {
//        connector = new SQLConnector();
//    }


    public void createProduct(User user, String sku, String name) {
        if (user instanceof Staff) {
            Staff staffUser = (Staff) user;
            staffUser.createProduct(sku, name);
        } else {
            // Handle the case where currentUser is not a Staff
            System.out.println("The user is not a staff and cannot update product inventory");

        }
    }

    public void updateProduct(User user, Product updatedProd, String name, String description, String vendor, String URL, String SKU, double price, String imgSrc) {
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            staff.updateProduct(updatedProd, name, description, vendor, URL, SKU, price, imgSrc);
        } else {
            // Handle the case where currentUser is not a Staff
            System.out.println("The user is not a staff and cannot update product inventory");

        }
    }

    public Product getProduct(String sku) {
        User user = new User();
        return user.getProduct(sku);
    }

    public Product getProductBySlug(String slug) {
        User user = new User();
        return user.getProductBySlug(slug);
    }

    public Cart getCart(User user) {
        if (user instanceof Staff) {
            System.out.println("The user is a staff and does not have a cart");
            return null;

        } else if(user instanceof Customer) {
            Customer customer = (Customer) user;
            return customer.getCart();
        } else{
            GuestUser guest = (GuestUser) user;
            return guest.getTempCart();
        }
    }

    public void addProductToCart(User user, String sku, int quantity) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.addProductToCart(sku, quantity);
        }
        if(user instanceof GuestUser){
            GuestUser guest = (GuestUser) user;
            guest.addProductToCart(sku, quantity);
        }
    }

    public void removeProductFromCart(User user, String sku) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.removeProductFromCart(sku);
        }
        if(user instanceof GuestUser){
            GuestUser guest = (GuestUser) user;
            guest.removeProductFromCart(sku);
        }
    }

    public void setProductQuantityInCart(User user, String sku, int qty) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.setProductQuantityInCart(sku, qty);
        }
        if(user instanceof GuestUser){
            GuestUser guest = (GuestUser) user;
            guest.setProductQuantityInCart(sku,qty);
        }
    }

    public void clearCart(User user) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.clearCart();
        }
        if(user instanceof GuestUser){
            GuestUser guest = (GuestUser) user;
            guest.clearCart();
        }
    }

    public File downloadProductCatalogue(User user) {
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return staff.downloadProductCatalog();
        } else {
            System.out.println("Not a staff, cannot download catalogue.");
            return null;
        }
    }

    public ArrayList<Product> getAllProducts(User user) {
        return user.getAllProducts();
    }

    public void createOrder(User user, String address) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.createOrder(customer, address);

        }else if(user instanceof GuestUser){
            GuestUser guest = (GuestUser) user;
            guest.createOrder(guest,address);

        }else {
            System.out.println("Cannot create an order for a non-customer user.");
        }
    }

    public List<Integer> getOrders(User user) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            return customer.getOrders(customer);
        }
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return staff.getOrders(staff);
        }

        return null;
    }

    public Order getOrder(User user, int orderId) {
        Order order=null;
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            int orderID = orderId;
            return customer.getOrder(customer, orderID);
        }
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return staff.getOrder(orderId);
        }
        return order;
    }

public void shipOrder(User user, int orderID, int trackingNumber){
        if(user instanceof Staff){
            Staff staff = (Staff) user;
            staff.shipOrder(orderID, trackingNumber);
        }else{
            System.out.println("You cannot ship an order! You're not a staff");
        }
}

//helper method to randomly generate tracking numbers
public int generateUniqueTrackingNumber() {
    // Generate a unique tracking number using a combination of timestamp and random component
    long timestamp = System.currentTimeMillis();
    int randomComponent = new Random().nextInt(1000000); // Adjust the range as needed
    return Math.abs((int) (timestamp % Integer.MAX_VALUE) * 1_000_000 + randomComponent);
}


public void setOrderOwner(int orderID, String userpasscode){
        //TO-DO
}

public void setPasscode(User user, String newpasscode){
        //TO-DO
}

public void changePermission(User user, User changedUser, String role){
        //TO-DO
}

}
