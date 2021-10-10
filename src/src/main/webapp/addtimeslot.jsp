<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.vaccineModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .form {
        font-size: 12px;
        padding-bottom: 10px;
        line-height: 30px;
        text-align: center
    }
</style>
<head>
    <title>Add timeslot</title>
</head>
<body>
<header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
    <h2>Add a timeslot</h2>
</header>
<div class="form">
    <%
        ArrayList vaccines = (ArrayList) request.getAttribute("vaccines");
    %>
    <form action = "add_timeslot" method = "post">
        Date: <input type = "date" name = "date" required><br/>
        From<input type = "time" name = "from" required>
        to<input type = "time" name = "to" required><br/>
        Number of shots available <input type = "int" name = "numberofshots" required>
        Vaccine Name: <select name="vname1" required>
                            <%
                                for(int i = 0;i<vaccines.size();i++){
                                    vaccineModel vaccine =(vaccineModel) vaccines.get(i);%>
                                    <option value=<%=vaccine.getName() %>><%=vaccine.getName() %></option>
                            <% }%>
                      </select><br/>
        <input type = "submit" value = "Add">
    </form>
    <form>
        <input type="button" value="Back to homepage" onclick="history.back()">
    </form>
</div>
</body>
</html>
