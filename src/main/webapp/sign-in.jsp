<%--
  Created by IntelliJ IDEA.
  User: chitchitm.czaw
  Date: 2023-10-25
  Time: 11:48 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index.css">
  <title>Sign-In</title>
</head>
<body>
<h1>Sign In</h1>
<div id="wrapper">
  <form id="form" action="LoginServlet" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input id="submit-btn" type="submit" value="Sign In">
  </form>
</div>
</form>
</body>
</html>

