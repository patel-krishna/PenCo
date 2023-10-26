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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>

<%
    storefrontFacade facade = (storefrontFacade) application.getAttribute("storefrontFacade");
    Customer user = (Customer) facade.getCurrentUser();
    Cart cart = user.getCart();
%>

<jsp:include page="navbar.jsp" />
<h1>Cart</h1>
<main>
<%
    if(!cart.getShoppingCart().isEmpty()){
        for (Product product : cart.getShoppingCart()) {
%>
<section class="product">
    <img src="<%=product.getImgSrc()%>" alt="<%=product.getName()%>">
    <p><%=product.getName()%></p>
    <p class="price">$<%=product.getPrice()%></p>
    <form action="${pageContext.request.contextPath}/removeProduct" method="post">
        <input type="hidden" name="sku" value="<%=product.getSKU()%>">
        <button type="submit">Remove</button>
    </form>
</section>
<%}
    }else{ %>
        <h2>Cart Is Empty</h2>
      <%
    }
%>
</main>

</body>
</html>
