<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<form action = "login" method = "post">
    User name: <input type = "text" name = "email" required><br/>
    Password: <input type = "password" name = "passWord" required><br/>
    <input type = "submit" value = "Login">
</form></br>
</body>
</html>