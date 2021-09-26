<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.timeSlotModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book a vaccination</title>
</head>
<body>
<%
    ArrayList timeslots = (ArrayList) request.getAttribute("timeslots");
%>
<form action = "bookvaccination" method = "post">
    Filter by Postcode: <input type = "text" name = "postcode" required><br/>
    <input type = "submit" value = "Filter">
</form></br>
<form action = "bookvaccination" method = "post">
    Filter by Health Care Provider: <input type = "text" name = "hcpname" required><br/>
    <input type = "submit" value = "Filter">
</form></br>
<table>
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>From</th>
        <th>To</th>
        <th>Provider</th>
        <th>Number of shots available</th>
        <th>Vaccine Name</th>
    </tr>

    <%
        for(int i = 0;i<timeslots.size();i++){
            timeSlotModel timeslot =(timeSlotModel) timeslots.get(i);%>
    <tr>
        <th><%=timeslot.getId() %></th>
        <th><%=timeslot.getDate() %></th>
        <th><%=timeslot.getFrom()%></th>
        <th><%=timeslot.getTo()%></th><br>
        <th><%=timeslot.getProvider()%></th><br>
        <th><%=timeslot.getNumberofshots()%></th><br>
        <th><%=timeslot.getVaccineName()%></th><br>
        <th>
            <a href="book?id=<%=timeslot.getId()%>&name=<%=timeslot.getVaccineName()%>" >Book</a>
        </th>
            <% }
  		 %>
</table>
</body>
</html>
