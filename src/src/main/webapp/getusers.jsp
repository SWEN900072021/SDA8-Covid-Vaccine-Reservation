<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.swen90008sda8.userModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <%
        ArrayList users = (ArrayList) request.getAttribute("users");
    %>
    <a href="get_user?vaccinated=True" οnclick= "return confirm('Are you sure about this deletion?')">Show vaccinated users</a>
    <a href="get_user?vaccinated=False" οnclick= "return confirm('Are you sure about this deletion?')">Show Not vaccinated users</a>
    <a href="get_user" οnclick= "return confirm('Are you sure about this deletion?')">Show All users</a>
<table>
    <tr>
        <th>email</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Identity</th>
        <th>Vaccinated</th>
    </tr>

    <%
        for(int i = 0;i<users.size();i++){
            userModel user =(userModel) users.get(i);%>
    <tr>
        <th><%=user.getEmail() %></th>
        <th><%=user.getFirstName()%></th>
        <th><%=user.getLastName()%></th>
        <th><%=user.getIdentity()%></th><br>
        <th><%=user.getVaccinated()%></th><br>
        <th>
            <a href="setvaccinated?email=<%=user.getEmail()%>" οnclick= "return confirm('Are you sure about this vaccination?')">Set vaccinated</a>
        </th>
        <th>
            <a href="setnotvaccinated?email=<%=user.getEmail()%>" οnclick= "return confirm('Are you sure about this vaccination?')">Set Not vaccinated</a>
        </th>
            <% }
  		 %>
</table>
</body>
</html>
