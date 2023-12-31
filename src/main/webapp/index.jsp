<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> PenCo | Home </title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com%22%3E/" >
    <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>

<div>
    <jsp:include page="navbar.jsp" />
</div>
<header>
    <h1>Welcome to PenCo</h1>
    <h3>Where Your Words Find Color: Discover Your Favorites at Our Stationery Store. Montreal based.</h3>
    <a href="${pageContext.request.contextPath}/products"><button class="button">Start Exploring</button></a>
</header>
</body>
</html>

<style>
    .button a{
        text-decoration: none;
    }

</style>