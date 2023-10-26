<%--
  Created by IntelliJ IDEA.
  User: Krish
  Date: 10/25/2023
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.business.*" %>
<html>
<head>
<%--test --%>
    <title>Sign-In</title>
</head>
<body>
<h1>User Sign-In</h1>
<form action="LoginServlet" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Sign In">
</form>
</body>
</html>
