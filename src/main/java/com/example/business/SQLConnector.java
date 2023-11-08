package com.example.business;
import java.sql.*;

public class SQLConnector {
    public Connection myDbConn;

    public SQLConnector() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.print("JDBC NOT FOUNDDD");
        }

        String url = "jdbc:mysql://penco.mysql.database.azure.com:3306/penco?useSSL=true";
        String username = "cuties387";
        String password = "Soen387!";

        try {
            myDbConn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void closeConnection() {
        try {
            if (myDbConn != null && !myDbConn.isClosed()) {
                myDbConn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        SQLConnector connector = new SQLConnector();

        try {
            Statement statement = connector.myDbConn.createStatement();

            // Execute a SELECT query
            String query = "SELECT * FROM products"; // Replace with your actual table name
            ResultSet resultSet = statement.executeQuery(query);

            // Print the results to the console
            while (resultSet.next()) {
                String id = resultSet.getString("SKU"); // Replace "SKU" with the actual column name
                String name = resultSet.getString("name"); // Replace "name" with the actual column name

                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the connection when you're done
            connector.closeConnection();
        }
    }
}
