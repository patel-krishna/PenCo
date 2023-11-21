<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.example.business.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>

<%
    User user = (User) application.getAttribute("User");
    storefrontFacade facade = new storefrontFacade();
    ArrayList<User> userList = facade.getAllUsers(user);
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Users</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<jsp:include page="navbar.jsp" />

<% if(user instanceof Staff){ %>
<div class="container">
    <div class="content">
        <h2>Users List</h2>
        <table border="1">
            <tr>
                <th>User ID</th>
                <th>User Passcode</th>
                <th>Current Role</th>
                <th>Change Role</th>
            </tr>
            <% for (int i=0; i<userList.size(); i++){
                    if(userList.get(i) instanceof Customer){
            %>
                <tr>
                    <td><%=((Customer) userList.get(i)).getUserId()%></td>
                    <td><%=((Customer) userList.get(i)).getPasscode()%></td>
                    <td>Customer</td>
                    <td>
                        <form action="<%=request.getContextPath()%>/userAccess" method="post">
                            <input type="hidden" name="userId" value="<%=((Customer) userList.get(i)).getUserId()%>">
                            <input type="hidden" name="isStaff" value="staff">
                            <button type="submit" class="button">Make Staff</button>
                        </form>
                    </td>
                </tr>
            <%      }else if(userList.get(i) instanceof Staff){ %>
                <tr>
                    <td><%=((Staff) userList.get(i)).getUserId()%></td>
                    <td><%=((Staff) userList.get(i)).getPasscode()%></td>
                    <td>Staff</td>
                    <td>
                        <form action="<%=request.getContextPath()%>/userAccess" method="post">
                            <input type="hidden" name="userId" value="<%=((Staff) userList.get(i)).getUserId()%>">
                            <input type="hidden" name="isStaff" value="customer">
                            <%-- if I currently signed in as this user I cannot make myself into a customer, another Staff member must do so     --%>
                            <% if(!Objects.equals(((Staff) user).getPasscode(), ((Staff) userList.get(i)).getPasscode())){%>
                                <button type="submit" class="button">Make Customer</button>
                            <% }else{%>
                                <button type="submit" class="button" disabled>Make Customer</button>
                            <% }%>
                        </form>
                    </td>
                </tr>
                  <%   }
                }
                %>
        </table>
    </div>
</div>
<% } else{ %>
    <h2>Sorry, you do not have access to this page!</h2>
<% }%>
</body>

<style>
    .container {
        display: flex;
        justify-content: center; /* Center horizontally */
    }

    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        background-color: rgba(255, 255, 255, 0.8); /* White with 80% opacity */
        padding: 20px;
        border-radius: 10px;
        margin: 20px;
    }

    table {
        background-color: white;
        border-collapse: collapse;
        width: 50vh;
        border-radius: 10px;
        /*margin-left: 10%;*/
        padding: 10px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:disabled {
        background-color: #ddd;
        cursor: not-allowed;
    }

    .button {
        display: block;
        margin-top: 20px;
        text-align: center;
    }
</style>
</html>