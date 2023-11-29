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
    <div class="container">
        <div class="content">
            <h2>Orders List</h2>
                <table border="1">
                    <tr>
                        <th>Order ID</th>
                        <th>View Order</th>
                    </tr>
                    <% for (int i=0; i<order_ids.size(); i++){ %>
                    <tr>
                        <td><%=order_ids.get(i)%></td>
                        <td><a class="button" href="orders/<%=order_ids.get(i)%>">View Order</a></td>
                    </tr>
                    <% } %>
                </table>
            <%
                if (user instanceof Customer) {
            %>
            <h3>Claim order by entering Order id: </h3>
            <form id="claimOrder" action="claimOrder" method="post">
                <input type="text" id="orderId" name="orderId" required>
                <button type="submit">Claim order</button>
            </form>
            <%
                }
            %>
        </div>
    </div>
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