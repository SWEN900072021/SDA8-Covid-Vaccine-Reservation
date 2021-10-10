<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:10
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
    <title>Add vaccine</title>
</head>

<body>
<header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
    <h2>Add a vaccine</h2>
</header>

<body>
    <div class="form">
        <form action = "add_vaccine" method = "post">
            Vaccine name: <input type = "text" name = "vaccinename" required><br/>
            Age-appropriate:<br/>
            From<input type = "text" name = "from" required><br/>
            to<input type = "text" name = "to" required><br/>
            <input type = "submit" value = "Add">
        </form>
        <form>
            <input type="button" value="Back to homepage" onclick="history.back()">
        </form>
    </div>
</body>
</html>
