<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
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

<br/>
<div class="wrapper">
    <div class="container">
        <h1><%= "COVID-19 Booking System" %></h1>
        <h2>Group SDA8</h2>
        <form class="form" name = "loginform" action = "" method = "post">
            <input  type = "text" placeholder="Username" name = "username" required><br/>
            <input type = "password" placeholder="Password" name = "password" required><br/>
            <input type = "submit" name = "submit" value = "Login">
        </form>
    </div>
    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./css/script.js"></script>
</body>
</html>