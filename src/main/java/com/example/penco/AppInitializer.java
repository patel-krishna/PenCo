package com.example.penco;
import com.example.business.*;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Initialize your storefrontFacade and set it as an attribute in the servlet context
        storefrontFacade facade = new storefrontFacade();
        context.setAttribute("storefrontFacade", facade);
        //context.setAttribute("products");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Perform cleanup tasks if necessary
    }
}
