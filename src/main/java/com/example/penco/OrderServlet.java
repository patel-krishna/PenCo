package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "order", value = "/orders/*")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        int orderID = Integer.parseInt(pathInfo.substring(1));
        System.out.println(orderID);

        ServletContext servletContext = getServletContext();
        User user = (User) servletContext.getAttribute("User");

        storefrontFacade facade = new storefrontFacade();
        Order order = facade.getOrder(user, orderID);

        // Set the products list as an attribute in the request
        request.setAttribute("order", order);
        request.setAttribute("order_id", orderID);

        // Forward the request to the JSP page responsible for displaying the products
        // Forward the request to the JSP page while keeping the URL as "/products"
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vieworder.jsp");
        dispatcher.forward(request, response);
    }

}
