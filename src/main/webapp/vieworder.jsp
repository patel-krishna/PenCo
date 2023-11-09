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

<%

  User user = (User) application.getAttribute("User");
  List<Integer> orderIds = ((Customer) user).getOrders2((Customer) user);

  //girl idk
  //List<Order> orderlist = (List<Order>) request.getAttribute("order_id");

  for (int orderId : orderIds) {
    Order displayOrder = ((Customer) user).getOrder((Customer) user, orderId);
%>

<h2>Order ID: <%= displayOrder.getOrderIdByUserId((Customer) user) %></h2>


<%
  // Assuming you have a method to get order items for the current order
  List<Order> orderItems = ((Customer) user).getOrderItems(displayOrder.getOrderId());

  // Display order items
  for (Order orderItem : orderItems) {
%>
<p>Product SKU: <%= orderItem.getProductSku() %></p>
<p>Quantity: <%= orderItem.getQuantity() %></p>
<!-- Add other order item details as needed -->
<%
  }
%>
<%
  }
%>