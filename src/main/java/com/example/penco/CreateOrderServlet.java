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

        User user = (User) servletContext.getAttribute("User");


         Cart cart = facade.getCart(user);


        // Get shipping address from form
        String shippingAddress = request.getParameter("shipping-address");
        System.out.println("Shipping: "+shippingAddress);

        //create new order with given user and inputted shipping address
        //Order newOrder = new Order(user, shippingAddress);
        //newOrder.setShoppingList(cart.getShoppingCart());
        facade.createOrder(user, shippingAddress);

        // Add user and shipping address to the request attributes
        request.setAttribute("user", user);
        request.setAttribute("shippingAddress", shippingAddress);


        // Clear users cart after order is placed
        facade.clearCart(user);

        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }
}