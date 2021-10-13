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
<header style="padding-top: 10px; padding-bottom: 10px;">
    <h2>Add a Healthcare Provider</h2>
</header>
    <div class="form">
        <form action = "signupprovider" method = "post">
            <table cellpadding="5">
                <tr>
                    <td>Email:</td>
                    <td><input class="easyui-textbox" type = "text" name = "email" required></td>
                </tr>
                <tr>
                    <td> Password:</td>
                    <td><input class="easyui-textbox" type="password" name = "passWord" required data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>Postcode:</td>
                    <td><input class="easyui-textbox" type="text" name = "postcode" required data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>Identity:</td>
                    <td><input class="easyui-textbox" value="Health Care Provider" readonly="readonly" type="text" name = "identity" required data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td> Type of provider: </td>
                    <td><select name="typeOfProvider" required>
                        <option value="null">-------</option>
                        <option value="Doctor or GP">Doctor or GP</option>
                        <option value="Hospital">Hospital</option>
                    </select></td>
                <tr>
                <tr>
                    <td>Organisation Name(If you are a Health Care Provider): </td>
                    <td> <input type = "text" name = "hcpName" required></td>
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
