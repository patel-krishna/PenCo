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

@WebServlet(name = "product", value = "/products/*")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
        String urlSlug = pathInfo.substring(1);
        System.out.println(urlSlug);

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = (storefrontFacade) servletContext.getAttribute("storefrontFacade");
        Product product = facade.getProductBySlug(urlSlug);

        // Set the products list as an attribute in the request
        request.setAttribute("product", product);

        // Forward the request to the JSP page responsible for displaying the products
        // Forward the request to the JSP page while keeping the URL as "/products"
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);

    }
}