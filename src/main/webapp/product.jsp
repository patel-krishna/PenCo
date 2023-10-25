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
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1><%=product.getName()%></h1>
<ul>
    <%
        if (product != null) {
    %>
    <li class="product-info"><%= product.getSKU() %>
        - <%= product.getName() %>
        - $<%= product.getPrice() %>
    </li>
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
    
    <a href="cart/products/<%= product.getName()%>">Edit Product</a>
    <%
        }else{
    %>
    <a href="${pageContext.request.contextPath}/sign-in.jsp">Please Sign In</a>
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
</html>