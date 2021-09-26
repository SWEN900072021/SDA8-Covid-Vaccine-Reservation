<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/26
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up A Recipient</title>
</head>
<body>
<form action = "signuprecipient" method = "post">
    Email: <input type = "text" name = "email" required><br/>
    Password: <input type = "password" name = "passWord" required><br/>
    Identity: <input type = "String" name = "identity" value="Recipient" readonly="readonly">
    Date of Birth: <input type = "date" name = "dateOfBirth" required><br/>
    First Name: <input type = "text" name = "firstName" required><br/>
    Last Name: <input type = "text" name = "lastName" required><br/>
    <input type = "submit" value = "sign up">
</form>
</body>
</html>
