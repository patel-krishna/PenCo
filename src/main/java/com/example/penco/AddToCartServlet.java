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

@WebServlet(name = "AddToCartServlet", value = "/cart/products/*")
public class AddToCartServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String slug = request.getParameter("slug");

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = (storefrontFacade) servletContext.getAttribute("storefrontFacade");
        Product cartProd = facade.getProductBySlug(slug);

        if (cartProd != null) {
            // Product Exists
            Customer currentUser = (Customer) facade.getCurrentUser();
            currentUser.addProductToCart(cartProd);

            System.out.print("Added to cart");
            // Redirect to a welcome or home page
            response.sendRedirect(request.getContextPath() + "/products/" + slug);

        } else {
            // Authentication failed
            request.setAttribute("error", "Adding to cart failed. Please try again.");
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }

}
