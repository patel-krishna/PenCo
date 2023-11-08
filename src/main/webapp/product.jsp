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
    User user = (User) application.getAttribute("User");
    Product product = (Product) request.getAttribute("product");
%>
<html>
<head>
    <title><%=product.getName()%></title>
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
        if (product != null) {
    %>
    <div class="container">
        <div class="image">
            <img src="<%= product.getImgSrc()%>" alt="Your Image">
        </div>
        <div class="information">
            <h2><%= product.getName() %></h2>
            <p><strong>Product ID: </strong><%= product.getSKU() %></p>
            <p><strong>Description: </strong><%= product.getDescription() %></p>
            <p><strong>Price: </strong>$<%= product.getPrice() %></p>
            <p><strong>Vendor: </strong> <%= product.getVendor() %></p>
<%--            USER CONTROLS BASED ON USER--%>
            <%
            if(user instanceof Customer) {
            %>
            <form action="${pageContext.request.contextPath}/cart/products/<%= product.getURL()%>" method="post">
                <!-- Hidden field to specify the product slug to be added to the cart -->
                <input type="hidden" name="slug" value="<%=product.getURL()%>">
                <label for="quantity">Qty:</label>
                <input type="number" name="quantity" id="quantity" min="1" required placeholder="1">
                <button class="button" type="submit">Add to Cart</button>
            </form>
            <%
            }else if(user instanceof Staff) {
            %>
            <h3>Update Product Info</h3>
            <form action="${pageContext.request.contextPath}/products/<%= product.getURL()%>" method="post" class="centered-form">

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

                <button type="submit" class="button">Update Product</button>
            </form>

            <%
            }else{
            %>
            <a class="si-prod" href="${pageContext.request.contextPath}/sign-in.jsp">Please sign in to add item to cart!</a>
            <%
                }
            %>
        </div>
    </div>

    <%
        }
    %>

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

    .button {
        color: #333;
        text-decoration: none;
        border: 2px solid #ccc;
        padding: 5px 10px;
        border-radius: 10px;
        background-color: #f5f5f5;
        display: inline-block;

  }


    /*Styling for Poduct Card*/
    .container {
        display: flex;
        align-items: center;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        margin: 20px;
    }

    .image {
        flex: 1;
        padding: 10px;
    }

    .image img {
        max-width: 100%;
        height: auto;
    }

    .information {
        flex: 2;
        padding: 10px;
    }

    .information h2 {
        font-size: 24px;
    }

    .information p {
        margin: 10px 0;
    }

    .centered-form{
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    textarea {
        margin: 10px; /* Add margin on all sides */
    }



</style>
</html>