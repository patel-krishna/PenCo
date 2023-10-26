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
<html>
<head>
    <title>Products</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1>All Products</h1>
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
