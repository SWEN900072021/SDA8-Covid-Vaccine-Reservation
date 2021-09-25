<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add time slots</title>
</head>
<body>
    <%
        ArrayList vaccines = (ArrayList) request.getAttribute("vaccines");
    %>
<form action = "add_timeslot" method = "post">
    Date: <input type = "date" name = "date" required><br/>
    From<input type = "time" name = "from" required>
    to<input type = "time" name = "to" required><br/>
    Number of shots available <input type = "int" name = "numberofshots" required>
    Vaccine Name: <select name="vname" required>
                        <option value="AstraZeneca">AstraZeneca</option>
                        <option value="Pfizer">Pfizer</option>
                  </select><br/>
    <input type = "submit" value = "Add">
</form>>
<a href="mainpage.jsp" οnclick= "return confirm('Are you sure about this deletion?')">Go back to main page</a>
</body>
</html>
