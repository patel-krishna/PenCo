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

//@WebServlet(name = "AddToCartServlet", value = "/cart/products/*")
public class AddToCartServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String slug = request.getParameter("slug");
        int quantity = request.getIntHeader("quantity");

        ServletContext servletContext = getServletContext();
        Customer user = (Customer) servletContext.getAttribute("User");
        storefrontFacade facade = new storefrontFacade();
        Product cartProd = facade.getProductBySlug(slug);

        if (cartProd != null) {
            // Product Exists

            //if the product is already in the cart
            if(user.getCart().getShoppingCart().containsKey(slug)){
                //return to page and tell customer
                response.sendRedirect(request.getContextPath() + "/products/" + slug);
                request.getSession().setAttribute("successMessage", "Product is already in cart!");
            }else{
                //add to cart
                facade.addProductToCart(user, cartProd.getSKU(),quantity);

                System.out.print("Added to cart");
                // Redirect to a product page
                response.sendRedirect(request.getContextPath() + "/products/" + slug);
                request.getSession().setAttribute("successMessage", "Product added  to cart successfully");
            }

        } else {
            // Authentication failed
            request.setAttribute("error", "Adding to cart failed. Please try again.");
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }

}
