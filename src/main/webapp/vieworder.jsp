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