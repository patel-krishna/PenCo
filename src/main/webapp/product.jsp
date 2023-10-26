<%@ page import="com.example.business.*" %>
<%@ page import="java.util.HashMap" %>

<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/24/2023
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    storefrontFacade facade = (storefrontFacade) application.getAttribute("storefrontFacade");
    User user = facade.getCurrentUser();
    Product product = (Product) request.getAttribute("product");
%>
<html>
<head>
    <title><%=product.getName()%></title>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1><%=product.getName()%></h1>
<ul class ="view-prod">
    <%
        if (product != null) {
    %>
    <h2>Update Product Info</h2>
<%--Example of how to display product info --%>
    <li class="product-info"><%= product.getSKU() %>
        - <%= product.getName() %>
        - $<%= product.getPrice() %>
        -<img class="view-image" src="<%= product.getImgSrc()%>" alt="view-image">
    </li>

    <div class="product-list">
    <p><%= product.getSKU() %> - <%= product.getName() %> - <%= product.getPrice() %> -<img class="view" src="<%= product.getImgSrc()%>">
    </p>
    </div>
    <%
        }
    %>

    <%
        if(user instanceof Customer) {
    %>
    <form action="/PenCo/cart/products/<%= product.getURL()%>" method="post">
        <!-- Hidden field to specify the product slug to be added to the cart -->
        <input type="hidden" name="slug" value="<%= product.getURL()%>">
        <button type="submit">Add to Cart</button>
    </form>
    <%
        }else if(user instanceof Staff) {
    %>
    <h2>Update Product Info</h2>
    <form action="${pageContext.request.contextPath}/products/<%= product.getURL()%>" method="post">

        <label for="sku">SKU:</label>
        <input type="text" id="sku" name="sku" value="<%= product.getSKU() %>">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= product.getName() %>">

        <label for="description">Description:</label>
        <textarea name="description" id="description"><%= product.getDescription() %></textarea>

        <label for="vendor">Vendor:</label>
        <input type="text" id="vendor" name="vendor" value="<%= product.getVendor() %>">

        <label for="url">URL Slug:</label>
        <input type="text" id="url" name="url" value="<%= product.getURL() %>">

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="<%= product.getPrice() %>">

        <label for="imgSrc">Image Source:</label>
        <input type="text" id="imgSrc" name="imgSrc" value="<%= product.getImgSrc() %>">

        <button type="submit" class="update-prod">Update Product</button>
    </form>


    <%
        }else{
    %>
    <a class="si-prod" href="${pageContext.request.contextPath}/sign-in.jsp">Please sign in to add item to cart!</a>
    <%
        }
    %>
</ul>
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
<style>
    body {
        text-align: center;
        background-image: none;
    }
    .si-prod {
        display: block;
        font-size: 20px;
        cursor: pointer;
        color: #333;
        text-decoration: none;
        padding: 10px 20px;
    }
    .view-prod{
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
    .view-image {
        width: 450px;
        height: 370px;
    }
    .update-prod {
        color: #333;
        text-decoration: none;
        border: 2px solid #ccc;
        padding: 5px 10px;
        border-radius: 10px;
        background-color: #f5f5f5;
        display: inline-block;
  }
</style>
</html>