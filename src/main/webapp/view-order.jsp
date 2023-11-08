<%--
  Created by IntelliJ IDEA.
  User: chitchitm.czaw
  Date: 2023-11-08
  Time: 10:24 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Order</title>
  <style>
    table, th, td {
      border: 1px solid black;
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
    .button:hover {
      background-color: #BDBDB7; /* Slightly darker background color on hover */
      color: #555; /* Slightly darker text color on hover */
    }

  </style>
</head>

<body>
<h2>View Orders</h2>
  <table style="width:50%">
    <tr>
      <th>User ID</th>
      <th>Order ID</th>
      <th>Shipping address</th>
    </tr>
    <tr>
      <td>1</td>
      <td>1</td>
      <td>saint catherine</td>
      <td><input type="button" value="View Details" name="order-details" /></td>
    </tr>

</table>

</body>

</html>
