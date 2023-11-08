<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.example.business.*" %>
<%@ page import="java.util.List" %>

<%
    List<Order> orders = DatabaseHelper.getOrdersFromDatabase();
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Orders</title>
</head>
<body>
<h1>Orders List</h1>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Customer ID</th>
        <th>Shipping Address</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.getOrderId()}</td>
            <td>${order.getCustomerId()}</td>
            <td>${order.getShippingAddress()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>