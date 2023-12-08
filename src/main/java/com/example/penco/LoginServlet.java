package com.example.penco;
import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = new storefrontFacade();

        SQLConnector connector = new SQLConnector();

        try{
            String query = "SELECT * FROM users WHERE passcode=?";
            PreparedStatement loginStatement = connector.myDbConn.prepareStatement(query);

//            loginStatement.setString(1,username);
            loginStatement.setString(1,password);

            ResultSet resultSet = loginStatement.executeQuery();

            if (resultSet.next()) {
                // Matching row found, perform your authentication logic here
                // For example, you can set a session attribute or redirect to a success page.
                // Authentication successful

                int isstaff = resultSet.getInt("isStaff");
                User loggedUser;

                if(isstaff == 1){
                    loggedUser = new Staff(password);
                }else{
                    loggedUser = new Customer(password);
                }

                //set in the session the User
                servletContext.setAttribute("User", loggedUser);

                // Redirect to a welcome or home page
                response.sendRedirect("index.jsp");

            } else {
                // Authentication failed
                request.setAttribute("error", "Authentication failed. Please try again.");
                request.setAttribute("Message", "User Passcode does not exist. Please Try Again.");
                request.getRequestDispatcher("sign-in.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connector.closeConnection(); // Add a method to close the database connection in your SQLConnector class
        }
    }
}
