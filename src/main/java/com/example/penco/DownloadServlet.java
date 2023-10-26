package com.example.penco;

import com.example.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "download", value = "/products/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        storefrontFacade facade = (storefrontFacade) servletContext.getAttribute("storefrontFacade");

        File file = facade.downloadProductCatalogue();

        /// Check if the file exists
        if (file.exists() && file.isFile()) {
            // Set response content type
            response.setContentType("application/xml");

            // Set response headers for file download
            response.setHeader("Content-Disposition", "attachment; filename=product_catalog.xml");

            // Read the file and write it to the response output stream
            try (InputStream is = new FileInputStream(file);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        // Forward the request to the JSP page responsible for displaying the products
        // Forward the request to the JSP page while keeping the URL as "/products"
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
        dispatcher.forward(request, response);
    }
}