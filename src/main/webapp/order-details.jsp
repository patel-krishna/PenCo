<%--
  Created by IntelliJ IDEA.
  User: chitchitm.czaw
  Date: 2023-11-08
  Time: 1:49 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details of the order</title>
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
<table>
  <tr>
    <td>Order Item</td>
    <td>pencil</td>
  </tr>
  <tr>
    <td>Quantity</td>
    <td>2</td>
    <td><button class="button" type="submit">Ship Order</button></td>
  </tr>



</table>
</body>
</html>
