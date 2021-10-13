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
<header style="padding-top: 10px; padding-bottom: 10px;">
    <h2>Add a Recipient</h2>
</header>
<div class="form">
    <form action = "signuprecipient" method = "post">
        <table cellpadding="5">
            <tr>
                <td>Email:</td>
                <td><input class="easyui-textbox" type = "text" name = "email" required></td>
            </tr>
            <tr>
                <td> Password: </td>
                <td><input class="easyui-textbox" type="password" name = "passWord" required data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>Identity:</td>
                <td><input class="easyui-textbox" value="Recipient" readonly="readonly" type="text" name = "identity" required data-options="required:true"></input></td>
            </tr>
            <tr>
                <td> Date of Birth: </td>
                <td><input type = "String" name = "" >
                    <input type = "date" name = "dateOfBirth" required></td>
            <tr>
            <tr>
                <td>First Name: </td>
                <td><input type = "text" name = "firstName" required></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type = "text" name = "lastName" required></td>
            </tr>
                <td>
                    <input iconCls="icon-save" toggle type = "submit" value = "sign up">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
