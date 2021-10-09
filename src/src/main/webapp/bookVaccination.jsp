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
<style>
    .table {
        margin-left: auto;
        margin-right: auto;
    }
</style>
<head>
    <title>Book a vaccination</title>
</head>
<body>
<header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
    <h2>Book a Vaccination Timeslot Below</h2>
</header>
<%
    ArrayList timeslots = (ArrayList) request.getAttribute("timeslots");
%>
<div style="text-align: center; padding-top: 10px">
<form action = "bookvaccination" method = "post">
    Filter by Postcode: <input type = "text" name = "postcode" required><br/>
    <input type = "submit" value = "Filter">
</form></br>
<form action = "bookvaccination" method = "post">
    Filter by Health Care Provider: <input type = "text" name = "hcpname" required><br/>
    <input type = "submit" value = "Filter">
</form></br>
<a href = "bookvaccination">Show all time slots<a><br>
<table class="table">
    <tr style="background-color: dimgrey">
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
        <td><%=timeslot.getId() %></td>
        <td><%=timeslot.getTimeRange().getDate() %></td>
        <td><%=timeslot.getTimeRange().getFrom()%></td>
        <td><%=timeslot.getTimeRange().getTo()%></td><br>
        <td><%=timeslot.getProvider()%></td><br>
        <td><%=timeslot.getNumberofshots()%></td><br>
        <td><%=timeslot.getVaccine().getName()%></td><br>
        <td>
            <a href="book?id=<%=timeslot.getId()%>&name=<%=timeslot.getVaccine()%>" >Book</a>
        </td>
            <% }
  		 %>
</table>
</div>
</body>
</html>
