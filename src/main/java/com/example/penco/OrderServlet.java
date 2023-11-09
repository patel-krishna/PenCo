package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="viewOrder", value="/order")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        User user = (User) servletContext.getAttribute("User");



        // Forward to your JSP page for displaying order details
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vieworder.jsp");
        dispatcher.forward(request, response);
    }
}
