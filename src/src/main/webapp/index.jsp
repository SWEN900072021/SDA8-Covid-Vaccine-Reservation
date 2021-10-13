<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="wrapper">
    <div class="container">
        <h1><%= "COVID-19 Booking System" %></h1>
        <h2>Group SDA8</h2>
        <form class="form" name = "loginform" action = "" method = "post">
            <input  type = "text" placeholder="Username" name = "username" required>
            <input type = "password" placeholder="Password" name = "password" required>
            <button type = "submit" name = "submit" value = "Login">Login</button>
        </form>
        <p style="font-family:monospace;color:#e54d4d;">
            <%
                if (request.getAttribute("shiroLoginFailure")!=null) {
            %>
            Username or password incorrect
            <%
                }
            %>
        </p >
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