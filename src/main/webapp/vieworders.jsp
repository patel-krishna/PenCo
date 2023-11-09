<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.example.business.*" %>
<%@ page import="java.util.List" %>

<%
    User user = (User) application.getAttribute("User");
    List<Integer> order_ids = (List<Integer>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Orders</title>
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
<body>
<h1>Orders List</h1>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>View Order</th>
    </tr>

    <% for (int i=0; i<order_ids.size(); i++){ %>

    <tr>
        <td><%=order_ids.get(i)%></td>
        <td><a class="button" href="orders/<%=order_ids.get(i)%>">View Order</a>
    </tr>
    <% } %>
    <%--    --%>
    <%--    <c:forEach items="${order_ids}" var="order">--%>
    <%--        <tr>--%>
    <%--            <td>${order.getOrderId()}</td>--%>
    <%--            <td>${order.getCustomerId()}</td>--%>
    <%--            <td>${order.getShippingAddress()}</td>--%>
    <%--        </tr>--%>
    <%--    </c:forEach>--%>
</table>
</body>
</html>