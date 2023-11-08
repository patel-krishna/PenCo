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


@WebServlet(name="viewOrders", value="/orders")
public class ViewOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = DatabaseHelper.getOrdersFromDatabase();
        request.setAttribute("orders", orders);

        ServletContext servletContext = getServletContext();
        User user = (User) servletContext.getAttribute("User");

        storefrontFacade facade = new storefrontFacade();

        List<Integer> order_ids = facade.getOrders(user);

        request.setAttribute("order_ids", order_ids);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/vieworders.jsp");
        dispatcher.forward(request, response);
    }
}
