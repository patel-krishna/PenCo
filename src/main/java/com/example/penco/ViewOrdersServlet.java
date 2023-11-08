package com.example.penco;
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
@WebServlet(name = "viewOrders", value = "/view-order")
public class ViewOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String shippingAddress = request.getParameter("shippingAddress");

        request.setAttribute("orders", getOrders());

        // Forward the request to the view orders JSP page
        request.getRequestDispatcher("/view-orders.jsp").forward(request, response);
    }

    private Object getOrders() {
        // Implement logic to retrieve orders data
        // For simplicity, I'm returning a dummy data
        // You should replace this with your actual logic to fetch orders from the database
        return null;
    }