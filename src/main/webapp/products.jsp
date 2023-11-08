<%@ page import="com.example.business.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/24/2023
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) application.getAttribute("User");
%>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1>All Products</h1>

<% if(user instanceof Staff) { %>
    <a class="button" href="${pageContext.request.contextPath}/products/download">Download Product Catalogue</a>
<% } %>

<ul>
    <%
        ArrayList<Product> productMap = (ArrayList<Product>) request.getAttribute("productMap");
        if (productMap != null) {
            for (Product entry : productMap) {
    %>

    <section class="product-card">
        <img src="<%= entry.getImgSrc() %>">
        <p><%= entry.getName() %></p>
        <p class="price">$<%= entry.getPrice() %></p>
        <a class="button" href="products/<%= entry.getURL() %>">View</a>
    </section>

    <%
            }
        }
    %>
</ul>
<style>
    .product-card {
        width: 300px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin: 10px;
        padding: 10px;
        text-align: center;
        background-color: #f5f5f5;
        float: left;
    }

    .product-card a {
        color: #333;
        text-decoration: none;
        border: 2px solid #ccc;
        padding: 5px 10px;
        border-radius: 10px;
        background-color: #f5f5f5;
        display: inline-block;
    }
    img {
        width: 100%;
    }

    .button {
        color: #333;
        text-decoration: none;
        border: 2px solid #ccc;
        padding: 5px 10px;
        border-radius: 10px;
        background-color: #f5f5f5;
        display: inline-block;

    }
</style>
</body>
</html>