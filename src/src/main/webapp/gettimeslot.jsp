<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.timeSlotModel" %>
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
    <title>Time slots</title>
</head>
<body>
<header style="padding-top: 10px; padding-bottom: 10px;">
    <h2>View all Current Timeslots</h2>
</header>
    <%
        ArrayList timeslots = (ArrayList) request.getAttribute("timeslots");
    %>
    <div style="text-align: center;">
        <table class="gridtable">
            <tr style="background-color: dimgrey">
                <th>Date</th>
                <th>From</th>
                <th>To</th>
                <th>Provider</th>
                <th>Number of shots available</th>
                <th>action</th>
            </tr>

            <%
                for(int i = 0;i<timeslots.size();i++){
                    timeSlotModel timeslot =(timeSlotModel) timeslots.get(i);%>
            <tr>
                <td><%=timeslot.getTimeRange().getDate() %></td>
                <td><%=timeslot.getTimeRange().getFrom()%></td>
                <td><%=timeslot.getTimeRange().getTo()%></td><br>
                <td><%=timeslot.getProvider()%></td><br>
                <td><%=timeslot.getNumberofshots()%></td><br>
                <td>
                    <a href="delete?date=<%=timeslot.getTimeRange().getDate()%>&from=<%=timeslot.getTimeRange().getFrom()%>&to=<%=timeslot.getTimeRange().getTo()%>
                            &provider=<%=timeslot.getProvider()%>" οnclick= "return confirm('Are you sure about this deletion?')">Delete</a>
                </td>
                    <% }
                 %>
        </table>
<%--        <form>--%>
<%--            <input type="button" value="Back to last page" onclick="history.back()">--%>
<%--        </form>--%>
    </div>
</body>
</html>
