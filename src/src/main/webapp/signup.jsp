<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/12
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - Hello signup</title>
</head>
<body>
<form action = "signup" method = "post">
    Email: <input type = "text" name = "email" required><br/>
    Password: <input type = "password" name = "passWord" required><br/>
    Date of Birth: <input type = "date" name = "dateOfBirth" required><br/>
    First Name: <input type = "text" name = "firstName" required><br/>
    Last Name: <input type = "text" name = "lastName" required><br/>
    Identity: <select name="identity" required>
                <option value="Recipient">Recipient</option>
                <option value="Health Care Provider">Health Care Provider</option>
              </select><br/>
    Postcode: <input type = "integer" name = "postcode"><br/>
    Type of provider: <select name="typeOfProvider">
                            <option value="null">-------</option>
                            <option value="Doctor or GP">Doctor or GP</option>
                            <option value="Hospital">Hospital</option>
                      </select><br/>
    Organisation Name(If you are a Health Care Provider): <input type = "text" name = "hcpName"><br/>
    <input type = "submit" value = "sign up">
</form>
</body>
</html>
