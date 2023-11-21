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

@WebServlet("/userAccess")
public class ChangeUserAccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user_id and isStaff from the request parameters
        int userId = Integer.parseInt(request.getParameter("userId"));
        String isStaff = request.getParameter("isStaff");

        ServletContext servletContext = getServletContext();
        Staff staff = (Staff) servletContext.getAttribute("User");
        storefrontFacade facade = new storefrontFacade();
        User changedUser = facade.getUserById(userId);

        facade.changePermission(staff, changedUser, isStaff);


        //servletContext.setAttribute("User", facade.getUserById(staff.getUserId()));

        // Redirect back to the page showing the user list
        response.sendRedirect(request.getContextPath() + "/users.jsp");
    }

}