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
<h1><%= "Welcome to PenCo" %></h1>
<p class="demo">Whatever you choose for your stationery is your favorite color because it's where you pour your heart out.</p>



<%--header template--%>
</body>
</html>