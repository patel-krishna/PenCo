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
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />

#DISPLAYS ORDER DETAILS, ALL PRODUCTS + CUSTOMER INFORMATION
<%
  Customer user = (Customer) application.getAttribute("User");
  Cart cart = user.getCart();
  storefrontFacade facade = new storefrontFacade();
%>
%>
<h1>Thank you for your order <%=user.getUsername()%></h1>
<h2>Your Order Details:</h2>

#DISPLAY CUSTOMERS ORDERED ITEMS
<%
  for (String key : cart.getShoppingCart().keySet()) {
      System.out.println(key);
      Product product = facade.getProduct(key);
%>
<%--<table>--%>
<%--  <thead>--%>
<%--  <tr>--%>
<%--    <th>Order Items:</th>--%>
<%--  </tr>--%>
<%--  </thead>--%>
<%--  <tbody>--%>
<%--  <tr>--%>
<%--    <td><img src="<%=product.getImgSrc()%>" alt="<%=product.getName()%>"></td>--%>
<%--    <td><%=product.getName()%></td>--%>
<%--    <td>$<%=product.getPrice()%></td>--%>
<%--  </tr>--%>
<%--  </tbody>--%>
<%--</table>--%>
<%--<%--%>
<%--  }--%>
<%--%>--%>
#DISPLAY CUSTOMER SHIPPING INFO


</body>
</html>
