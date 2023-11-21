package com.example.business;
import java.sql.*;

public class SQLConnector {
    public Connection myDbConn;

    public SQLConnector() {

        try{
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException e){
            System.out.print("JDBC NOT FOUND");
        }

        String url = "jdbc:sqlite:/Users/briannam/Documents/GitHub/PenCo/src/main/webapp/WEB-INF/penco.sqlite";
        //String username = "cuties387";
        //String password = "Soen387!";

        try {
            myDbConn = DriverManager.getConnection(url);
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
            String query = "SELECT * FROM Products"; // Replace with your actual table name
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
