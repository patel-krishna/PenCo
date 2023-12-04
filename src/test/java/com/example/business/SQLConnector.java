package com.example.business;
import java.sql.*;

public class SQLConnector {
    public Connection myDbConn;

    public SQLConnector() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.print("JDBC NOT FOUND");
        }


        String url = "jdbc:sqlite:/Users/briannam/Documents/GitHub/PenCo/src/test/java/com/example/business/penco_test.sqlite";
        ///jdbc:sqlite:/Users/briannam/Documents/GitHub/PenCo/src/test/java/com/example/business/penco_test.sqlite
        //jdbc:sqlite:C:/Users/Krish/Documents/School/Concordia/Projects/PenCo/src/test/java/com/example/business/penco_test.sqlite

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

}
