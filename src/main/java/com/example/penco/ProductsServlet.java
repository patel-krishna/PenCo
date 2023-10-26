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

@WebServlet(name = "products", value = "/products")
public class ProductsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Your code to retrieve and display the list of products goes here

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = (storefrontFacade) servletContext.getAttribute("storefrontFacade");
        HashMap<String, Product> productMap = facade.getAllProductsSku();

        // Set the products list as an attribute in the request
        request.setAttribute("productMap", productMap);

        // Forward the request to the JSP page responsible for displaying the products
        // Forward the request to the JSP page while keeping the URL as "/products"
        RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
        dispatcher.forward(request, response);

    }
}