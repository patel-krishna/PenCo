<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index.css">
</head>
<body>
<div>
    <jsp:include page="navbar.jsp" />
</div>
<div><h1><%= "Welcome to PenCo" %></h1></div>

    <h1>Please Sign In</h1>
    <div id="wrapper">
        <form id="form" action="LoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <input id="submit-btn" type="submit" value="Sign In">
        </form>
    </div>

<%--header template--%>
</body>
</html>