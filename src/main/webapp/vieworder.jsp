<%--
  Created by IntelliJ IDEA.
  User: chitchitm.czaw
  Date: 2023-11-08
  Time: 6:43 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.business.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title> PenCo | Home </title>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com%22%3E/" >
  <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />

<%
  User user = (User) application.getAttribute("User");
  Order order = (Order) request.getAttribute("order");
  int order_id = (int) request.getAttribute("order_id");
%>

<h2>Order ID: <%=order_id%></h2>
<h3>Shipping address: <%=order.getShippingAddress()%> </h3>

<table border="1">
  <tr>
    <th>Product</th>
    <th>Quantity</th>
  </tr>

  <%
    // Retrieve the productMap from the request
    HashMap<String, Integer> productMap = order.getShoppingList();

    // Iterate over the map and print table rows
    for (Map.Entry<String, Integer> entry : productMap.entrySet()) {
  %>
  <tr>
    <td><%= entry.getKey() %></td>
    <td><%= entry.getValue() %></td>
  </tr>
  <%
    }
  %>

</table>
</body>
</html>