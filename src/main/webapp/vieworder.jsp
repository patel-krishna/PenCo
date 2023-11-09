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

<!DOCTYPE html>
<html>
<head>
  <title> View Order </title>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com%22%3E/" >
  <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<style>
  body {
    font-family: 'Josefin Sans', sans-serif;
    background-color: #f2f2f2;
    margin: 0;
  }

  .container {
    display: flex;
    justify-content: center; /* Center horizontally */
  }

  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.8); /* White with 80% opacity */
    padding: 20px;
    border-radius: 10px;
    margin: 20px;
  }

  table {
    background-color: white;
    border-collapse: collapse;
    width: 50vh;
    border-radius: 10px;
    /*margin-left: 10%;*/
    padding: 10px;
  }

  th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }

  th {
    background-color: #f2f2f2;
  }

  img {
    max-width: 50px;
    max-height: 50px;
  }

  form {
    width: 10vh;
    margin-right: 10%;
    padding: 10px;
  }

  button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  button:disabled {
    background-color: #ddd;
    cursor: not-allowed;
  }

  .button {
    display: block;
    margin-top: 20px;
  }
</style>
</head>
<body>
<jsp:include page="navbar.jsp" />
<%
  storefrontFacade facade = new storefrontFacade();
  User user = (User) application.getAttribute("User");
  Order order = (Order) request.getAttribute("order");
  int order_id = (int) request.getAttribute("order_id");

  boolean isStaff = (user != null && (user instanceof Staff));
  boolean orderShipped =  order.isOrderShipped(order_id);
%>

<div class="container">

  <div class="content">
    <h2>Order ID: <%=order_id%></h2>
    <h3>Shipping address: <%=order.getShippingAddress()%></h3>
    <table border="1">
      <tr>
        <th>Product</th>
        <th>Product SKU</th>
        <th>Product Name</th>
        <th>Quantity</th>
      </tr>

      <%
        // Retrieve the productMap from the request
        HashMap<String, Integer> productMap = order.getShoppingList();

        for (Map.Entry<String, Integer> entry : productMap.entrySet()) {
        Product productTemp = facade.getProduct(entry.getKey());
      %>
      <tr>
        <td><img src="<%= productTemp.getImgSrc() %>"></td>
        <td><%= productTemp.getSKU() %></td>
        <td><%= productTemp.getName() %></td>
        <td><%= entry.getValue() %></td>
      </tr>
      <% } %>
    </table>
  </div>

  <%if(isStaff){ %>

  <form action="${pageContext.request.contextPath}/orders/<%=order_id%>" method="post">
    <!-- Include a hidden input field to store the order ID -->
    <input type="hidden" name="orderID" value="<%= order_id %>">
    <!-- Display the button if the user is a staff member and the order has not been shipped -->
    <%
      if (!orderShipped) {
    %>
    <button class="button" type="submit">Ship Order</button>
    <%
    } else {
    %>
    <button class="button" type="submit" disabled>Order Shipped</button>
    <%
        }
      }
    %>

  </form>

</div>
</body>
<script>
  // Check if the success message is present in the session
  var successMessage = "<%= request.getSession().getAttribute("successMessage") %>";

  if (successMessage && successMessage.trim() !== "null") {
    // Display a JavaScript alert with the success message
    alert(successMessage);

    // Clear the success message from the session to prevent it from showing again
    <% request.getSession().removeAttribute("successMessage"); %>
  }
</script>
</html>