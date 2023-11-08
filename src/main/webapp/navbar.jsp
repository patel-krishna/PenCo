<%@ page import="com.example.business.User" %>
<%@ page import="com.example.business.storefrontFacade" %>
<%@ page import="com.example.business.Customer" %>
<%@ page import="com.example.business.Staff" %><%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/24/2023
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar">
    <%
        User user = (User) application.getAttribute("User");

        if (user instanceof Customer) {
    %>
    <div class="navbar">
        <div class="logo">
            <h2>PenCo.</h2>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/products">All Products</a></li>
            <li><a href="${pageContext.request.contextPath}/orders">View Your Orders</a></li>
            <li><a href="${pageContext.request.contextPath}/cart.jsp">Cart</a></li>
            <li>Welcome, <%=user.getUsername()%> </li>
        </ul>
    </div>

    <%
    } else if (user instanceof Staff) {
    %>
    <div class="navbar">
        <div class="logo">
            <h2>PenCo.</h2>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/products">All Products</a></li>
            <li><a href="${pageContext.request.contextPath}/create-product.jsp">Create Product</a></li>
            <li><a href="${pageContext.request.contextPath}/orders">View Orders</a></li>
            <li>Welcome, staff member <%=user.getUsername()%>!</li>
        </ul>
    </div>
    <%
    } else {
    %>

    <div class="navbar">
        <div class="logo">
            <h2>PenCo.</h2>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/products">All Products</a></li>
            <li><a href="${pageContext.request.contextPath}/sign-in.jsp">Sign in</a></li>
        </ul>
    </div>

    <%
        }
    %>
</div>
