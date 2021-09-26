<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/26
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up A Health Care Provider</title>
</head>
<body>
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
</body>
</html>
