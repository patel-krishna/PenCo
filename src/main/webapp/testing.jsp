<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/24/2023
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.business.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<table>
    <tr>
        <th>SKU</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <%-- Iterate over the map entries using a scriptlet --%>
    <%
        // Get the storefrontFacade from the application scope
        com.example.business.storefrontFacade facade = (com.example.business.storefrontFacade) application.getAttribute("storefrontFacade");

        // Get the HashMap<String, Product>
        java.util.HashMap<String, com.example.business.Product> productMap = facade.getAllProductsSku();
    %>

    <%-- Iterate over the productMap entries --%>
    <% for (java.util.Map.Entry<String, com.example.business.Product> entry : productMap.entrySet()) { %>
    <tr>
        <td><%= entry.getKey() %></td>
        <td><%= entry.getValue().getName() %></td>
        <td><%= entry.getValue().getDescription() %></td>
        <td><%= entry.getValue().getPrice() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
