package com.example.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

public class storefrontFacade {

    public SQLConnector connector;

    public storefrontFacade() {
        connector = new SQLConnector();
    }


    public void createProduct(User user, String sku, String name) {
        if (user instanceof Staff) {
            Staff staffUser = (Staff) user;
            staffUser.createProduct(sku, name);
        } else {
            // Handle the case where currentUser is not a Staff
            System.out.println("The user is not a staff and cannot update product inventory");

        }
    }

    public void updateProduct(User staff, Product updatedProd, String name, String description, String vendor, String URL, String SKU, double price, String imgSrc) {
        if (staff instanceof Staff) {
            Staff staffUser = (Staff) staff;
            staffUser.updateProduct(updatedProd, name, description, vendor, URL, SKU, price, imgSrc);
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

        } else {
            Customer customer = (Customer) user;
            return customer.getCart();
        }
    }

    public void addProductToCart(User user, String sku, int quantity) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.addProductToCart(sku, quantity);
        }
    }

    public void removeProductFromCart(User user, String sku) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.removeProductFromCart(sku);
        }
    }

    public void setProductQuantityInCart(User user, String sku, int qty) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.setProductQuantityInCart(sku, qty);
        }
    }

    public void clearCart(User user) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.clearCart();
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
        } else {
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
        }
}

}
