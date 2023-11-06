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

@WebServlet(name = "removeProduct", value = "/removeProduct")
public class RemoveFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sku = request.getParameter("sku");

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = new storefrontFacade();
        User user = (User) servletContext.getAttribute("User");

        if (sku != null) {

            facade.removeProductFromCart(user,sku);

            // Redirect to a welcome or home page
            response.sendRedirect("cart.jsp");

        } else {
            // Authentication failed
            request.setAttribute("error", "Action failed. Please try again.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}