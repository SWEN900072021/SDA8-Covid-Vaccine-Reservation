<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<p>
    <%
        if (request.getAttribute("shiroLoginFailure")!=null) {
    %>
    Username or password incorrect
    <%
        }
    %>
</p >
<header style="text-align: center; background-color: lightpink" >
    <h1><%= "COVID-19 Booking System" %></h1>
    <h2>Group SDA8</h2>
</header>
<br/>
<form style="text-align: center" name = "loginform" action = "" method = "post">
    User name: <input  type = "text" name = "username" required><br/>
    <div style="padding-bottom: 20px"></div>
    Password: <input type = "password" name = "password" required><br/>
    <div style="padding-bottom: 20px"></div>
    <input type = "submit" name = "submit" value = "Login">
</form></br>
</body>
</html>