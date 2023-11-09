package com.example.penco;

import jakarta.servlet.annotation.WebServlet;

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
public class ViewOrderServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        try {
            int orderID = Integer.parseInt(pathInfo.substring(1));

            ServletContext servletContext = getServletContext();
            User user = (User) servletContext.getAttribute("User");

            storefrontFacade facade = new storefrontFacade();
            Order order = facade.getOrder(user, orderID);

            // Set the products list as an attribute in the request
            request.setAttribute("order", order);
            request.setAttribute("order_id", orderID);

            // Forward the request to the JSP page responsible for displaying the products
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vieworder.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            // Handle the case where the pathInfo does not represent a valid integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int order_id = Integer.parseInt(request.getParameter("orderID"));

        String pathInfo = request.getPathInfo();
        int orderID = Integer.parseInt(pathInfo.substring(1));

        ServletContext servletContext = getServletContext();
        Staff user = (Staff) servletContext.getAttribute("User");
        storefrontFacade facade = new storefrontFacade();
        int trackingNum = Math.abs(facade.generateUniqueTrackingNumber());

        facade.shipOrder(user,order_id,trackingNum);

        response.sendRedirect(request.getContextPath() + "/orders/" + order_id);
        request.getSession().setAttribute("successMessage", "Order has been shipped!");

    }

}
