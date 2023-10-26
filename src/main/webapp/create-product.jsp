<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/26/2023
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index.css">
</head>

<body>
<h2>Create Product Form</h2>
<div id="wrapper">
<form id="form" action="/PenCo/create-product" method="post">

    <label for="sku">SKU:</label>
    <input type="text" id="sku" name="sku" value="">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="">

    <label for="description">Description:</label>
    <textarea name="description" id="description"></textarea>

    <label for="vendor">Vendor:</label>
    <input type="text" id="vendor" name="vendor">

    <label for="url">URL Slug:</label>
    <input type="text" id="url" name="url">

    <label for="price">Price:</label>
    <input type="text" id="price" name="price">

    <label for="imgSrc">Image Source:</label>
    <input type="text" id="imgSrc" name="imgSrc">
    <input type="submit" value="Creat Product">
</form>
</div>>
</body>
</html>
