<%@ page import="com.example.business.*" %>
<%--
  Created by IntelliJ IDEA.
  User: briannam
  Date: 2023-11-07
  Time: 1:19 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Order Page</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>

<%
  Customer user = (Customer) application.getAttribute("User");
  Cart cart = user.getCart();
  storefrontFacade facade = new storefrontFacade();
  String shippingAddress = (String) request.getAttribute("shippingAddress");
%>
<jsp:include page="navbar.jsp" />
<header>
  <h2>Thank you for your order <%= user.getUsername() %> ! </h2>
  <h3>Your Shipping Address: <%= shippingAddress %></h3>
</header>

</body>

</html>