package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.nio.file.FileStore;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(name = "claimOrder", value = "/claimOrder")
public class ClaimOrderServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = new storefrontFacade();

        User user = (Customer) servletContext.getAttribute("User");

        // get orderId from form
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        facade.claimOrder(user, orderId);

        //redirect to vieworder page where user will see claimed order
        response.sendRedirect(request.getContextPath() + "/orders" );
        request.getSession().setAttribute("successMessage", "Order is now claimed!");
    }
}