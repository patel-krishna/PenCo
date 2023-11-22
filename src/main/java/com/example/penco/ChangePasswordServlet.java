package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.nio.file.FileStore;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(name = "changePassword", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        storefrontFacade facade = new storefrontFacade();
        User user = (User) servletContext.getAttribute("User");
        String passcode= request.getParameter("passcode");
        facade.setPasscode(user, passcode);
        response.sendRedirect(request.getContextPath() + "/users.jsp");
        request.getSession().setAttribute("successMessage", "Passcode is now changed!");
    }
}
