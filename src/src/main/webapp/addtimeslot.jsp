<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add time slots</title>
</head>
<body>
<form action = "add_timeslot" method = "post">
    Date: <input type = "date" name = "date" required><br/>
    From<input type = "time" name = "from" required>
    to<input type = "time" name = "to" required>
    <input type = "submit" value = "Add">
</form>>
</body>
</html>
