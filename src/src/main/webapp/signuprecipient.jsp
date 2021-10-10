<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/26
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Sign up A Recipient</title>
</head>

<body>
<header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
    <h2>Add a Recipient</h2>
</header>
<div class="form">
    <form action = "signuprecipient" method = "post">
        Email: <input type = "text" name = "email" required><br/>
        Password: <input type = "password" name = "passWord" required><br/>
        Identity: <input type = "String" name = "identity" value="Recipient" readonly="readonly">
        Date of Birth: <input type = "date" name = "dateOfBirth" required><br/>
        First Name: <input type = "text" name = "firstName" required><br/>
        Last Name: <input type = "text" name = "lastName" required><br/>
        <input type = "submit" value = "sign up">
    </form>
    <form>
        <input type="button" value="Back to homepage" onclick="history.back()">
    </form>
</div>
</body>
</html>
