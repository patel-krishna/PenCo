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
<jsp:include page="navbar.jsp" />
<%
  Customer user = (Customer) application.getAttribute("User");
  Cart cart = user.getCart();
  storefrontFacade facade = new storefrontFacade();
%>

<%--DISPLAY CUSTOMERS CART ITEMS--%>
<table>
  <thead>
  <tr>
    <th>Get it Shipped:</th>
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
  </tr>
  <%
    }
  %>
  </tbody>
</table>


<%--#INPUT CUSTOMER SHIPPING INFO--%>
<form id="form" action="${pageContext.request.contextPath}/create-order" method="post">

  <label for="address">Address Line:</label>
  <input type="text" id="address" name="shipping-address" required><br>

  <a href="${pageContext.request.contextPath}/order.jsp"><button type="submit" class="submit-order">Submit Order</button></a>
</form>
</body>

<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    padding: 8px;
    text-align: left;
  }
  th {
    background-color: #f2f2f2;
  }
  tr:nth-child(even) {
    background-color: #f2f2f2;
  }
  img {
    max-width: 100px;
    max-height: 100px;
  }
</style>
</html>
