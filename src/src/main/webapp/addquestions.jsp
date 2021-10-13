<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/13
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.vaccineModel" %>
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
    <title>Add questions</title>
</head>
<body>
<header style="padding-top: 10px; padding-bottom: 10px;">
    <h2>Add a question</h2>
</header>
<div class="form">
    <%
        ArrayList vaccines = (ArrayList) request.getAttribute("vaccines");
    %>
    <form action = "add_question" method = "post">
        <table cellpadding="5">
            <tr>
                <td>Vaccine Name:</td>
                <td><select name="vname1" required>
                    <%
                        for(int i = 0;i<vaccines.size();i++){
                            vaccineModel vaccine =(vaccineModel) vaccines.get(i);%>
                    <option value=<%=vaccine.getName() %>><%=vaccine.getName() %></option>
                    <% }%>
                </select></td>
            </tr>
            <tr>
                <td>Question body:</td>
                <td><input class="easyui-textbox" type="text" name = "questionBody" required data-options="required:true"></input></td>
            </tr>
            <tr>
                <td> Desired Answer:</td>
                <td>
                    <input  class="easyui-textbox" name="answer" type="radio" value="true" checked="checked">True
                    <input name="answer" type="radio" value="false">False
            </tr>
            <tr>
                <td>
                    <input iconCls="icon-save" toggle type = "submit" value = "Add">
                </td>
            </tr>
        </table>
    </form>>
<%--    <a href="adminpage.jsp" οnclick= "return confirm('Are you sure about this deletion?')">Go back to admin page</a>--%>
</div>
</body>
</html>
