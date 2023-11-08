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

@WebServlet(name = "createProduct", value = "/create-product")
public class CreateProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sku = request.getParameter("sku");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String vendor = request.getParameter("vendor");
        String url = request.getParameter("url");
        double price = Double.parseDouble(request.getParameter("price"));
        String img = request.getParameter("imgSrc");

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = new storefrontFacade();
        User user = (User) servletContext.getAttribute("User");

        //create a product with only sku and name, added to the HashMap
        facade.createProduct((Staff) user, sku, name);

        //Get this new product and update its attributes with the rest of the info
        Product product = facade.getProduct(sku);
        facade.updateProduct(user,product,name,description,vendor,url,sku,price,img);

        response.sendRedirect(request.getContextPath() + "/products/" + url);
        request.getSession().setAttribute("successMessage", "Product has been created!");

    }
}