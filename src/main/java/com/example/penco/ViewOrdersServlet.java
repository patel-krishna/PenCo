package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="vieworders", value="/orders")
public class ViewOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        User user = (User) servletContext.getAttribute("User");

        storefrontFacade facade = new storefrontFacade();
        if(user instanceof Customer){
            List<Integer> orders = facade.getOrders(user);
            // Set the 'orders' attribute so we can use in jsp
            request.setAttribute("orders", orders);
        }

        if(user instanceof Staff){
            List<Integer> orders = facade.getOrders(user);
            // Set the 'orders' attribute so we can use in jsp
            request.setAttribute("orders", orders);
        }

        // Forward to your JSP page for displaying orders
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewOrders.jsp");
        dispatcher.forward(request, response);
    }
}