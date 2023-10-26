<%--
  Created by IntelliJ IDEA.
  User: chitchitm.czaw
  Date: 2023-10-23
  Time: 5:42 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.Element" %>
<%@ page import="org.w3c.dom.NodeList" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Base64" %>

<%
  String username = request.getParameter("username");
  String password = request.getParameter("password");

  // Load XML file
  InputStream xmlStream = application.getResourceAsStream("/WEB-INF/users.xml");
  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  Document document = factory.newDocumentBuilder().parse(xmlStream);

  // Find user in XML
  NodeList users = document.getElementsByTagName("user");
  boolean isValidUser = false;

  for (int i = 0; i < users.getLength(); i++) {
    Element user = (Element) users.item(i);
    String xmlUsername = user.getElementsByTagName("username").item(0).getTextContent();
    String xmlPassword = user.getElementsByTagName("password").item(0).getTextContent();

    // Check if credentials match
    if (username.equals(xmlUsername) && Base64.getEncoder().encodeToString(password.getBytes()).equals(xmlPassword)) {
      isValidUser = true;
      break;
    }
  }

  if (isValidUser) {
    session.setAttribute("username", username);
    response.sendRedirect("welcome.jsp");
  } else {
    out.println("Invalid username or password. Please try again.");
  }
%>

</body>
</html>
