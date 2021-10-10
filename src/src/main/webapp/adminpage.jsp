<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .link {
        font-size: 20px;
        padding-bottom: 10px;
        line-height: 30px;
        text-align: center
    }
</style>
<head>
    <title>Admin Page</title>
</head>
<body>
<header style=" background-color: darksalmon; padding-top: 20px; padding-bottom: 20px; text-align: center">
    <h1>Admin Homepage</h1>
</header>
<div class="link">
    <a href="add_vaccine">Add new vaccine type </a></br>
    <a href="signuprecipient.jsp">Create new recipient account </a></br>
    <a href="signupprovider.jsp">Create new provider account </a></br>
    <a href="get_timeslot">View all time slots </a></br>
    <a href="get_user">View all users </a></br>
    <form id="logout_form" name="logout_form"
          action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"></br>
    </form>
</div>
</body>
</html>
