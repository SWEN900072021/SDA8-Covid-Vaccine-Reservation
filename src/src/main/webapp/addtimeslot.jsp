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
    <title>Add timeslot</title>
</head>
<body>
<header style="padding-top: 10px; padding-bottom: 10px;">
    <h2>Add a timeslot</h2>
</header>
<div class="form">
    <%
        ArrayList vaccines = (ArrayList) request.getAttribute("vaccines");
    %>
    <form action = "add_timeslot" method = "post">
        <table cellpadding="5">
            <tr>
                <td>Date: </td>
                <td><input type = "date" name = "date" required></td>
            </tr>
            <tr>
                <td>From:</td>
                <td><input type = "time" name = "from" required></td>
            </tr>
            <tr>
                <td>to</td>
                <td>
                    <input type = "time" name = "to" required>
            </tr>
            <tr>
                <td>Number of shots available:</td>
                <td><input type = "int" name = "numberofshots" required></td>
            </tr>
            <tr>
                <td> Vaccine Name:</td>
                <td>
                    <select name="vname1" required>
                        <%
                            for(int i = 0;i<vaccines.size();i++){
                                vaccineModel vaccine =(vaccineModel) vaccines.get(i);%>
                        <option value=<%=vaccine.getName() %>><%=vaccine.getName() %></option>
                        <% }%>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input iconCls="icon-save" toggle type = "submit" value = "Add">
                </td>
            </tr>
        </table>

    </form>
<%--    <form>--%>
<%--        <input type="button" value="Back to homepage" onclick="history.back()">--%>
<%--    </form>--%>
</div>
</body>
</html>
