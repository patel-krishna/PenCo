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
    Product product = (Product) request.getAttribute("product");
%>
<html>
<head>
    <title><%=product.getName()%></title>
</head>
<body>
<h1><%=product.getName()%></h1>
<ul>
    <%
        if (product != null) {
    %>
    <li class="product-info"><%= product.getSKU() %>
        - <%= product.getName() %>
        - $<%= product.getPrice() %>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>