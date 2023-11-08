package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.nio.file.FileStore;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateOrder", value = "/create-order")
public class CreateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        storefrontFacade facade = new storefrontFacade();

        // Get user's cart
        Customer user = (Customer) servletContext.getAttribute("User");
        Cart cart = facade.getCart(user);

        // Get shipping address from the form
        String shippingAddress = request.getParameter("shipping_address");

        // Create order with shipping address
        user.createOrder(user,shippingAddress);

        // Store the new order in the database
        facade.createOrder(user, shippingAddress);

        // Clear users cart after order is placed
        user.clearCart();

        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }
}