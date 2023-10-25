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
        storefrontFacade facade = (storefrontFacade) application.getAttribute("storefrontFacade");
        User user = facade.getCurrentUser();

        if (user instanceof Customer) {
    %>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/products">All Products</a></li>
        <li><a href="cart">Cart</a></li>
        <li>Welcome, <%=user.getUsername()%> </li>
    </ul>
    <%
    } else if (user instanceof Staff) {
    %>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/products">All Products</a></li>
        <li><a href="create-products.jsp">Create Product</a></li>
        <li>Welcome, staff member <%=user.getUsername()%>!</li>
    </ul>
    <%
    } else {
    %>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/products">All Products</a></li>
        <li><a href="${pageContext.request.contextPath}/sign-in.jsp">Sign in</a></li>
    </ul>
    <%
        }
    %>
</div>


