package com.example.business;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Staff extends User{

    public Staff(String username, String password){
        super(username, password);
    }

    public void createProduct(String sku, String name){
        SQLConnector connector = new SQLConnector();
        try {
            // Define the SQL insert statement
            String insertQuery = "INSERT INTO products (SKU, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(insertQuery);

            // Set the values for the new product
            preparedStatement.setString(1, sku);
            preparedStatement.setString(2, name);

            // Execute the insert
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Product created successfully.");
            } else {
                System.out.println("Product creation failed.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * If the attribute is not to be updated, set it to an empty string or null
     * @param updatedProd
     * @param name
     * @param description
     * @param URL
     * @param SKU
     * @param price
     * @param imgSrc
     */
    public void updateProduct(Product updatedProd,String name, String description, String vendor, String URL, String SKU, double price, String imgSrc){
        if(!name.isBlank()){
            updatedProd.setName(name);
        }
        if(!description.isBlank()){
            updatedProd.setDescription(description);
        }
        if(!vendor.isBlank()){
            updatedProd.setVendor(vendor);
        }
        if(!URL.isBlank()){
            updatedProd.setURL(URL);
        }
        if(!SKU.isBlank()){
            updatedProd.setSKU(SKU);
        }
        if(price != 0){
            updatedProd.setPrice(price);
        }
        if(!imgSrc.isBlank()){
            updatedProd.setImgSrc(imgSrc);
        }

        SQLConnector connector = new SQLConnector();

        try {
            // Define the SQL update statement
            String updateQuery = "UPDATE products SET name = ?, description = ?, vendor = ?, url_slug = ?, price = ?, imgSrc = ? WHERE SKU = ? ";
            PreparedStatement preparedStatement = connector.myDbConn.prepareStatement(updateQuery);

            // Set the values for the product
            preparedStatement.setString(1, updatedProd.getName());
            preparedStatement.setString(2, updatedProd.getDescription());
            preparedStatement.setString(3, updatedProd.getVendor());
            preparedStatement.setString(4, updatedProd.getURL());
            preparedStatement.setDouble(5, updatedProd.getPrice());
            preparedStatement.setString(6, updatedProd.getImgSrc());
            preparedStatement.setString(7, updatedProd.getSKU());

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product update failed. No matching SKU found.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public File downloadProductCatalog(HashMap<String, Product> allProductsSku, String filePath){

        writeProductsToXML(allProductsSku, filePath);
        return new File(filePath);
    }

    public static void writeProductsToXML(HashMap<String, Product> productMap, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            if(!productMap.isEmpty()){
                writer.newLine();
                writer.write("<products>");
                writer.newLine();

                for (String productId : productMap.keySet()) {
                    Product product = productMap.get(productId);

                    writer.write("  <product>");
                    writer.newLine();
                    writer.write("    <name>" + product.getName() + "</name>");
                    writer.newLine();
                    writer.write("    <description>" + product.getDescription() + "</description>");
                    writer.newLine();
                    writer.write("    <vendor>" + product.getVendor() + "</vendor>");
                    writer.newLine();
                    writer.write("    <slug>" + product.getURL() + "</slug>");
                    writer.newLine();
                    writer.write("    <sku>" + product.getSKU() + "</sku>");
                    writer.newLine();
                    writer.write("    <Price>" + product.getPrice() + "</Price>");
                    writer.newLine();
                    writer.write("    <img>" + product.getImgSrc() + "</img>");
                    writer.newLine();
                    writer.write("  </product>");
                    writer.newLine();
                }
            }
            writer.write("</products>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
