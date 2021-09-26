<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.userModel" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.vaccineModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%
    ArrayList users = (ArrayList) request.getAttribute("users");
    ArrayList vaccines = (ArrayList) request.getAttribute("vaccines");
%>
<a href="get_user" οnclick= "return confirm('Are you sure about this deletion?')">Show All users</a><br>
<form action = "get_user" method = "post">
    Vaccine User plan to inject: <select name="vname" required>
    <%
        for(int i = 0;i<vaccines.size();i++){
            vaccineModel vaccine =(vaccineModel) vaccines.get(i);%>
    <option value=<%=vaccine.getName() %>><%=vaccine.getName() %></option>
    <% }%>
</select><br/>
    <input type = "submit" value = "View">
</form><br>
<p>Now viewing <%=(String)request.getAttribute("viewing") %>
<table>
    <tr>
        <th>email</th>
        <th>|First name</th>
        <th>|Last name</th>
        <th>|Identity</th>
        <th>|Vaccinated</th>
        <th>|Booked slot ID</th>
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
        <th><%=user.getTimeslotID()%></th><br>
            <% }
  		 %>
</table>
</body>
</html>
