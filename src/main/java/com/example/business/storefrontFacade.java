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
    public static HashMap<String,Product> allProductsSku;

    final String filePathProducts = "C:\\Users\\Krish\\Documents\\School\\Concordia\\Projects\\PenCo\\src\\main\\resources\\products.xml";
    final String filePathUsers = "C:\\Users\\Krish\\Documents\\School\\Concordia\\Projects\\PenCo\\src\\main\\resources\\users.xml";

    static HashMap<String, User> allUsers;
    static User currentUser;

    public storefrontFacade(){
        allProductsSku = productInitialization(filePathProducts);
        allUsers = userInitialization(filePathUsers);
        currentUser = null;
    }

    public void createProduct(Staff staff, String sku, String name) {
        if (currentUser instanceof Staff) {
            Staff staffUser = (Staff) currentUser;
            staffUser.createProduct(allProductsSku, sku, name);
        } else {
            // Handle the case where currentUser is not a Staff
            // You could throw an exception or handle it in some other way
            System.out.println("The user is not a staff and cannot update product inventory");

        }
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
            return staff.downloadProductCatalog(allProductsSku, filePathProducts);
        }else{
            System.out.println("Not a staff, cannot download catalogue.");
            return null;
        }
    }

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

}

