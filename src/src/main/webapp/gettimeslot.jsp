<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.swen90008sda8.Models.timeSlotModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time slots</title>
</head>
<body>
<%
    ArrayList timeslots = (ArrayList) request.getAttribute("timeslots");
%>

<table>
    <tr>
        <th>Date</th>
        <th>From</th>
        <th>To</th>
        <th>Provider</th>
        <th>Number of shots available</th>
    </tr>

    <%
        for(int i = 0;i<timeslots.size();i++){
            timeSlotModel timeslot =(timeSlotModel) timeslots.get(i);%>
    <tr>
        <th><%=timeslot.getDate() %></th>
        <th><%=timeslot.getFrom()%></th>
        <th><%=timeslot.getTo()%></th><br>
        <th><%=timeslot.getProvider()%></th><br>
        <th><%=timeslot.getNumberofshots()%></th><br>
        <th>
            <a href="delete?date=<%=timeslot.getDate()%>&from=<%=timeslot.getFrom()%>&to=<%=timeslot.getTo()%>
                    &provider=<%=timeslot.getProvider()%>" οnclick= "return confirm('Are you sure about this deletion?')">Delete</a>
        </th>
            <% }
  		 %>
</table>
</body>
</html>
