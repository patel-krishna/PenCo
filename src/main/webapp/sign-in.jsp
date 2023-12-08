<%--
  Created by IntelliJ IDEA.
  User: chitchitm.czaw
  Date: 2023-10-25
  Time: 11:48 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.business.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
    <title>Sign-In</title>
</head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />
<%
    User user = (User) application.getAttribute("User");
%>

<%if(user instanceof GuestUser){%>
<h1>Sign-In</h1>
<form id="form" action="LoginServlet" method="post">

    <label for="password">User Passcode:</label>
    <input type="password" id="password" name="password" required>

    <input type="submit" value="Sign In"><br>

</form>
<%}%>

<%if(!(user instanceof GuestUser)){%>
<h1>Change Passcode</h1>
<form id="form" action="ChangePasswordServlet" method="post">
    <label for="passcode">New Passcode:</label>
    <input type="passcode" id="passcode" name="passcode">
    <input type="submit" value="Change Passcode">
</form>
<%}%>
<p style="text-align: center"> ${Message} </p>
</div>
</body>
</html>

