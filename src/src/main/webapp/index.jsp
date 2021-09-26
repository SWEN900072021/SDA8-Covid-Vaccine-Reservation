<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<header style="text-align: center; background-color: lightpink" >
    <h1><%= "COVID-19 Booking System" %></h1>
    <h2>Group SDA8</h2>
</header>
<br/>
<form style="text-align: center" action = "login" method = "post">
    User name: <input 20px" type = "text" name = "email" required><br/>
    <div style="padding-bottom: 20px"></div>
    Password: <input type = "password" name = "passWord" required><br/>
    <div style="padding-bottom: 20px"></div>
    <input type = "submit" value = "Login">
</form></br>
</body>
</html>