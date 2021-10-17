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
    table.gridtable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
    }
    table.gridtable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #dedede;
    }
    table.gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #ffffff;
    }
</style>
<head>
    <title>Users</title>
</head>
<body>
    <header style="padding-top: 10px; padding-bottom: 10px;">
        <h2>View all recipients booked your timeslot</h2>
    </header>

    <%
        ArrayList users = (ArrayList) request.getAttribute("users");
    %>

    <div style="text-align: center;">
        <a href="get_user?vaccinated=True" οnclick= "return confirm('Are you sure about this deletion?')">Show vaccinated users    |</a>
        <a href="get_user?vaccinated=False" οnclick= "return confirm('Are you sure about this deletion?')">Show Not vaccinated users    |</a>
        <a href="get_user" οnclick= "return confirm('Are you sure about this deletion?')">Show All users    </a>
        <table class="gridtable" style="padding-left: 10px">
            <tr>
                <th>email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Identity</th>
                <th>Vaccinated</th>
                <th>action</th>
                <th>action</th>
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
<%--        <form>--%>
<%--            <input type="button" value="Back to last page" onclick="history.back()">--%>
<%--        </form>--%>
    </div>
</body>
</html>
