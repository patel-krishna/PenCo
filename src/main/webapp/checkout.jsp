<%@ page import="com.example.business.*" %>
<%--
  Created by IntelliJ IDEA.
  User: briannam
  Date: 2023-11-07
  Time: 1:12 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Checkout</title>
  <meta charset="UTF-8">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp"/>

<%
  Customer user = (Customer) application.getAttribute("User");
  Cart cart = user.getCart();
  storefrontFacade facade = new storefrontFacade();
%>
  <%--DISPLAY CUSTOMERS CART ITEMS--%>
  <div class="container">
    <div class="content">
      <h2>Your Order</h2>
        <table>
          <thead>
          <tr>
            <th></th>
            <th>Product name:</th>
            <th>Product price:</th>
            <th>Quantity ordered:</th>
          </tr>
          </thead>
          <tbody>
          <%
            for (String key : cart.getShoppingCart().keySet()) {
              System.out.println(key);
              Product product = facade.getProduct(key);
          %>
          <tr>
            <td class="p-img"><img src="<%=product.getImgSrc()%>" alt="<%=product.getName()%>"></td>
            <td class="p-name"><%=product.getName()%></td>
            <td class="p-price">$<%=product.getPrice()%></td>
            <td class="p-qty"><%=cart.getShoppingCart().get(key)%></td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
    </div>
      <%--#INPUT CUSTOMER SHIPPING INFO--%>
      <form id="form" action="${pageContext.request.contextPath}/create-order" method="post">

        <label for="address">Address Line:</label>
        <input type="text" id="address" name="shipping-address" required><br>

        <a href="${pageContext.request.contextPath}/order.jsp"><button type="submit" class="submit-order" class="button">Submit Order</button></a>
      </form>
  </div>
</body>

<style>
  body {
    font-family: 'Josefin Sans', sans-serif;
    background-color: #f2f2f2;
    margin: 0;
  }

  .container {
    display: flex;
    flex-direction: column; /* Stack children vertically */
    align-items: center; /* Center horizontally */
    justify-content: center; /* Center vertically */
    margin: 20px;
    text-align: center;
  }

  .content {
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
    margin-top: 20px; /* Adjust the top margin */
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
    display: flex; /* Use flexbox for the form */
    flex-wrap: wrap; /* Wrap items to the next line if there's not enough space */
    justify-content: center; /* Center items horizontally */
    margin-top: 20px; /* Adjust the top margin */
  }

  label {
    margin-right: 5px; /* Add some space between label and input */
  }

  input, button {
    padding: 10px;
    margin-left: 10px; /* Add some space between input and button */
  }

  button {
    background-color: lightpink;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  button:hover {
    background-color: #bdbdb7; /* Slightly darker background color on hover */
    color: #555;
    cursor: pointer;
  }
</style>

</html>
