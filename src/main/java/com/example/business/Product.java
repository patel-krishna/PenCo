package com.example.business;

public class Product {
    String name;
    String description;
    String URL;
    String SKU;
    double price;
    String imgSrc;

    //Constructors
    public Product(String name, String description, String URL, String SKU, double price, String imgSrc) {
        this.name = name;
        this.description = description;
        this.URL = URL;
        this.SKU = SKU;
        this.price = price;
        this.imgSrc = imgSrc;
    }

    //default constructor
    public Product(){
        this.name = null;
        this.description = null;
        this.URL = null;
        this.SKU = null;
        this.price = 0;
        this.imgSrc = null;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public double getPrice() {
        return price;
    }

    public Product(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
