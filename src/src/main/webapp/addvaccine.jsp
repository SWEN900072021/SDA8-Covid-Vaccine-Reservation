<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add vaccine</title>
</head>
<body>
<form action = "add_vaccine" method = "post">
    Vaccine name: <input type = "text" name = "vaccinename" required><br/>
    Age-appropriate:<br/>
    From<input type = "text" name = "from" required>
    to<input type = "text" name = "to" required>
    <input type = "submit" value = "Add">
</form>
</body>
</html>
