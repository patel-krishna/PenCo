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
        String quantityStr = request.getParameter("quantity");
        int quantity = Integer.parseInt(quantityStr);

        ServletContext servletContext = getServletContext();
        Customer user = (Customer) servletContext.getAttribute("User");
        storefrontFacade facade = new storefrontFacade();
        Product cartProd = facade.getProductBySlug(slug);

        if (cartProd != null) {
            // Product Exists

            //if the product is already in the cart
            if(user.getCart().getShoppingCart().containsKey(cartProd.getSKU())){
                //return to page and tell customer

                facade.setProductQuantityInCart(user,cartProd.getSKU(), quantity);
                response.sendRedirect(request.getContextPath() + "/cart.jsp");
                request.getSession().setAttribute("successMessage", "Product is already in cart! Quantity updated.");
            }else{
                //add to cart
                facade.addProductToCart(user, cartProd.getSKU(),quantity);

                System.out.println("Added to cart");
                // Redirect to a product page
                response.sendRedirect(request.getContextPath() + "/products/" + slug);
                request.getSession().setAttribute("successMessage", "Product added to cart successfully");
            }

        } else {
            // Authentication failed
            request.setAttribute("error", "Adding to cart failed. Please try again.");
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }

}
