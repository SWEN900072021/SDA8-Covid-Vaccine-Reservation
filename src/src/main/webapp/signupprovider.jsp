<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/26
  Time: 0:15
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
    <title>Sign up A Health Care Provider</title>
</head>
<body>
<header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
    <h2>Add a Healthcare Provider</h2>
</header>
<div class="form">
<form action = "signupprovider" method = "post">
    Email: <input type = "text" name = "email" required><br/>
    Password: <input type = "password" name = "passWord" required><br/>
    Postcode: <input type = "integer" name = "postcode" required><br/>
    Identity: <input type = "String" name = "identity" value="Health Care Provider" readonly="readonly">
    Type of provider: <select name="typeOfProvider" required>
                        <option value="null">-------</option>
                        <option value="Doctor or GP">Doctor or GP</option>
                        <option value="Hospital">Hospital</option>
                      </select><br/>
    Organisation Name(If you are a Health Care Provider): <input type = "text" name = "hcpName" required><br/>
    <input type = "submit" value = "sign up">
</form>
</div>
</body>
</html>
