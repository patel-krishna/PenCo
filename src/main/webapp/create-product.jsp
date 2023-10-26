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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

</head>

<body>
<jsp:include page="navbar.jsp" />
<h2>Create Product Form</h2>
<div id="wrapper">

    <form id="form" action="${pageContext.request.contextPath}/create-product" method="post">

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
        <input type="number" step="0.01" id="price" name="price">

        <label for="imgSrc">Image Source:</label>
        <input type="text" id="imgSrc" name="imgSrc">
        <input type="submit" class="submit-prod" value="Create Product">
    </form>
</div>>
</body>
</html>
