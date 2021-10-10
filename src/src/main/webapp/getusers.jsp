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
<style>
    .table {
        margin-left: auto;
        margin-right: auto;
    }
</style>
<head>
    <title>Users</title>
</head>
<body>
    <header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
        <h2>View all System users</h2>
    </header>

    <%
        ArrayList users = (ArrayList) request.getAttribute("users");
    %>

    <div style="text-align: center;">
        <a href="get_user?vaccinated=True" οnclick= "return confirm('Are you sure about this deletion?')">Show vaccinated users    |</a>
        <a href="get_user?vaccinated=False" οnclick= "return confirm('Are you sure about this deletion?')">Show Not vaccinated users    |</a>
        <a href="get_user" οnclick= "return confirm('Are you sure about this deletion?')">Show All users    </a>
        <table class="table" style="padding-left: 10px">
            <tr style="background-color: dimgrey">
                <th>email</th>
                <th>|First name</th>
                <th>|Last name</th>
                <th>|Identity</th>
                <th>|Vaccinated</th>
            </tr>

            <%
                for(int i = 0;i<users.size();i++){
                    userModel user =(userModel) users.get(i);%>
            <tr>
                <td><%=user.getEmail() %></td>
                <td><%=user.getFirstName()%></td>
                <td><%=user.getLastName()%></td>
                <td><%=user.getIdentity()%></td><br>
                <td><%=user.getVaccinated()%></td><br>
                <td>
                    <a href="setvaccinated?email=<%=user.getEmail()%>" οnclick= "return confirm('Are you sure about this vaccination?')">Set vaccinated</a>
                </td>
                <td>
                    <a href="setnotvaccinated?email=<%=user.getEmail()%>" οnclick= "return confirm('Are you sure about this vaccination?')">Set Not vaccinated</a>
                </td>
            </tr>
                    <% }
                 %>
        </table>
        <form>
            <input type="button" value="Back to last page" onclick="history.back()">
        </form>
    </div>
</body>
</html>
