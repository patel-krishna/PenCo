<%@ page import="com.example.business.*" %>
<%@ page import="java.util.HashMap" %>

<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/26/2023
  Time: 12:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
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
%>

<jsp:include page="navbar.jsp" />
<h1>Cart</h1>
<main>
<%
    if(!cart.getShoppingCart().isEmpty()){
        for (String key : cart.getShoppingCart().keySet()) {
            System.out.println(key);
            Product product = facade.getProduct(key);
%>
<section class="product">
    <img src="<%=product.getImgSrc()%>" alt="<%=product.getName()%>">
    <p><%=product.getName()%></p>
    <p class="price">$<%=product.getPrice()%></p>

    <form action="cart/products/<%= product.getURL()%>" method="post">
        <input type="hidden" name="slug" value="<%=product.getURL()%>">
        <label for="quantity">Qty:</label>
        <input type="number" name="quantity" id="quantity" min="1" placeholder="<%=cart.getShoppingCart().get(key)%>">
        <button class="button" type="submit">Update Quantity</button>
    </form>

    <form action="${pageContext.request.contextPath}/removeProduct" method="post">
    <input type="hidden" name="sku" value="<%=product.getSKU()%>">
    <button class="button" type="submit">Remove</button>
    </form>

</section>
<%}
    }else{ %>
        <h2>Cart Is Empty</h2>
      <%
    }
%>
    <section class="checkout-section">
        <a href="${pageContext.request.contextPath}/checkout.jsp"><button>Checkout Order</button></a>
    </section>
</main>

<style>
    .button {
        color: #333;
        text-decoration: none;
        border: 2px solid #ccc;
        padding: 5px 10px;
        border-radius: 10px;
        background-color: #f5f5f5;
        display: inline-block;

    }
</style>

</body>
</html>
