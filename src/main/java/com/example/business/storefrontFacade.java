package com.example.business;

import java.util.HashMap;
import java.io.File;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

public class storefrontFacade {

    public SQLConnector connector;

    public storefrontFacade(){
       connector = new SQLConnector();
    }

    public void createProduct(User staff, String sku, String name) {
        if (staff instanceof Staff) {
            Staff staffUser = (Staff) staff;
            staffUser.createProduct(sku, name);
        } else {
            // Handle the case where currentUser is not a Staff
            System.out.println("The user is not a staff and cannot update product inventory");

        }
    }
    public void updateProduct(User staff, Product updatedProd, String name, String description, String vendor, String URL, String SKU, double price, String imgSrc){
        if (staff instanceof Staff) {
            Staff staffUser = (Staff) staff;
            staffUser.updateProduct(updatedProd, name, description, vendor, URL, SKU, price, imgSrc);
        } else {
            // Handle the case where currentUser is not a Staff
            System.out.println("The user is not a staff and cannot update product inventory");

        }
    }

    public Product getProduct(String sku){
        User user = new User();
        return user.getProduct(connector,sku);
    }

    public Product getProductBySlug(String slug){
        User user = new User();
        return user.getProductBySlug(connector,slug);
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

    public void addProductToCart(User user, String sku, int quantity){
        if(user instanceof Customer){
            Customer customer = (Customer) user;
            customer.addProductToCart(sku,quantity);
        }
    }

    public void removeProductFromCart(User user, String sku){
        if(user instanceof Customer){
            Customer customer = (Customer) user;
            customer.removeProductFromCart(sku);
        }
    }

    public void setProductQuantityInCart(User user, String sku, int qty){
        if(user instanceof Customer){
            Customer customer = (Customer) user;
            customer.setProductQuantityInCart(sku,qty);
        }
    }

    public void clearCart(User user){
        if(user instanceof Customer){
            Customer customer = (Customer) user;
            customer.clearCart();
        }
    }

    public File downloadProductCatalogue(User user){
        if(user instanceof Staff){
            Staff staff = (Staff) user;
            return staff.downloadProductCatalog();
        }else{
            System.out.println("Not a staff, cannot download catalogue.");
            return null;
        }
    }










    //functions for when we were using in-app memory
    public HashMap<String,Product> productInitialization(String filePath) {
        HashMap<String, Product> productMap = new HashMap<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();


            NodeList productList = doc.getElementsByTagName("product");

            for (int i = 0; i < productList.getLength(); i++) {
                Element productElement = (Element) productList.item(i);
                String sku = productElement.getElementsByTagName("sku").item(0).getTextContent();
                String name = productElement.getElementsByTagName("name").item(0).getTextContent();
                String description = productElement.getElementsByTagName("description").item(0).getTextContent();
                String vendor = productElement.getElementsByTagName("vendor").item(0).getTextContent();
                String slug = productElement.getElementsByTagName("slug").item(0).getTextContent();
                double price = Double.parseDouble(productElement.getElementsByTagName("price").item(0).getTextContent());
                String img = productElement.getElementsByTagName("img").item(0).getTextContent();

                Product product = new Product(name, description, vendor, slug, sku, price, img);
                productMap.put(sku, product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productMap;
    }

    public static HashMap<String, User> userInitialization(String filePath) {
        HashMap<String, User> userMap = new HashMap<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList userList = doc.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {
                Element userElement = (Element) userList.item(i);
                String username = userElement.getElementsByTagName("username").item(0).getTextContent();
                String password = userElement.getElementsByTagName("password").item(0).getTextContent();
                String role = userElement.getElementsByTagName("role").item(0).getTextContent();

                User user;
                if ("customer".equals(role)) {
                    user = new Customer(username, password);
                } else {
                    user = new Staff(username, password);
                }
                userMap.put(username, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }

    public HashMap<String, Product> getAllProductsSku() {
        return allProductsSku;
    }

    public HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}


