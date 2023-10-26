<%@ page import="com.example.business.*" %>
<%@ page import="java.util.HashMap" %>

<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/24/2023
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  storefrontFacade facade = (storefrontFacade) application.getAttribute("storefrontFacade");
  User user = facade.getCurrentUser();
%>
<html>
<head>
    <title>Products</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1>All Products</h1>

<% if(user instanceof Staff) { %>
  <a href="${pageContext.request.contextPath}/products/download">Download Product Catalogue</a>
<% } %>

<ul>
  <%
    HashMap<String, Product> productMap = (HashMap<String, Product>) request.getAttribute("productMap");
    if (productMap != null) {
      for (HashMap.Entry<String, Product> entry : productMap.entrySet()) {
  %>
  <li class="product-card"><%= entry.getKey() %>
    - <%= entry.getValue().getName() %>
    - $<%= entry.getValue().getPrice() %>
    - <a href="products/<%= entry.getValue().getURL() %>">View</a></li>
  <%
      }
    }
  %>
</ul>
</body>
</html>
