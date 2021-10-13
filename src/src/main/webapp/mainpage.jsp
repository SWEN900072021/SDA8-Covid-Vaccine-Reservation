<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .link {
        font-size: 20px;
        padding-bottom: 10px;
        line-height: 30px;
        text-align: center;
    }
</style>
<head>
    <title>Recipient Home Page</title>
</head>
<header style=" background-color: darksalmon; padding-top: 20px; padding-bottom: 20px; text-align: center">
    <h1>Welcome to the COVID-19 booking centre</h1>
    <h3>Book or view your proof of vaccination below</h3>
</header>
<body>
<div class="link">
    <a href="answer_question">View all timeslots and book a vaccination </a></br>
    <a href="showcertification">Show proof of vaccination </a></br>
<%--    <a href="logout">Sign out </a></br>--%>
    <form id="logout_form" name="logout_form"
          action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"></br>
    </form>
</div>
</body>
</html>
