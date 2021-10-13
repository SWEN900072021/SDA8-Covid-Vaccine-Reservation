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
<header style="padding-top: 10px; padding-bottom: 10px; ">
    <h2>Add a vaccine</h2>
</header>
    <div class="form">
        <form action = "add_vaccine" method = "post">
            <table cellpadding="5">
                <tr>
                    <td>Vaccine name:</td>
                    <td><input class="easyui-textbox" type="text" name = "vaccinename" required data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>From:</td>
                    <td><input class="easyui-textbox" type="text" name = "from" required data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>to:</td>
                    <td><input class="easyui-textbox" type="text" name = "to" required data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>
                        <input iconCls="icon-save" toggle type = "submit" value = "Add">
                    </td>
                </tr>
                </table>


        </form>
    </div>
</body>
</html>
