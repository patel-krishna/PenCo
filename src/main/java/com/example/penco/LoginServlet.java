package com.example.penco;
import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform user authentication here (e.g., checking credentials against a database)
        // You may need to replace this with your actual authentication logic.

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = (storefrontFacade) servletContext.getAttribute("storefrontFacade");
        HashMap<String, User> allUsers = facade.getAllUsers();

        if (allUsers.get(username).getPassword().equals(password)) {
            // Authentication successful
            User loggedUser = allUsers.get(username) ;
            facade.setCurrentUser(loggedUser);

            // Redirect to a welcome or home page
            response.sendRedirect("index.jsp");

        } else {
            // Authentication failed
            request.setAttribute("error", "Authentication failed. Please try again.");
            request.getRequestDispatcher("sign-in.jsp").forward(request, response);
        }
    }
}
