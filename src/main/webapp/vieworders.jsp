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
    <style>
        table, td, th {
            border: 1px solid black;


        }

        table {
            border-collapse: collapse;
            width: 30%;
            margin: 0 auto; /* This will center the table horizontally */
        }




    </style>

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
        <%--        <th>Customer ID</th>--%>
        <%--        <th>Shipping Address</th>--%>
    </tr>

    <% for (int i=0; i<order_ids.size(); i++){ %>

    <tr>
        <td style="text-align: center;"><%=order_ids.get(i)%></td>
        <td style="text-align: center;"> <!-- Add this style for center alignment -->
            <form action="${pageContext.request.contextPath}/orders/<%=order_ids.get(i)%>" method="post">
                <!-- Hidden field to specify the product slug to be added to the cart -->
                <input type="hidden" name="order_id" value="<%=order_ids.get(i)%>">
                <button class="button" type="submit">View Order Details</button>
            </form>
        </td>
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

<style>
    .container {
        display: flex;
        justify-content: center; /* Center horizontally */
    }

    .content {
        display: flex;
        flex-direction: column;
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
        /*margin-left: 10%;*/
        padding: 10px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:disabled {
        background-color: #ddd;
        cursor: not-allowed;
    }

    .button {
        display: block;
        margin-top: 20px;
        text-align: center;
    }
</style>
</html>