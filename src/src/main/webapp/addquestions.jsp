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
<header style="background-color: darksalmon; padding-top: 10px; padding-bottom: 10px; text-align: center">
    <h2>Add a question</h2>
</header>
<div class="form">
    <%
        ArrayList vaccines = (ArrayList) request.getAttribute("vaccines");
    %>
    <form action = "add_question" method = "post">
        Vaccine Name: <select name="vname1" required>
                            <%
                                for(int i = 0;i<vaccines.size();i++){
                                    vaccineModel vaccine =(vaccineModel) vaccines.get(i);%>
                            <option value=<%=vaccine.getName() %>><%=vaccine.getName() %></option>
                            <% }%>
                     </select><br/>
        Question body: <input type = "text" name = "questionBody" required><br/>
        Desired Answer: <input name="answer" type="radio" value="true" checked="checked">True
                        <input name="answer" type="radio" value="false">False
        <input type = "submit" value = "Add">
    </form>>
    <a href="adminpage.jsp" οnclick= "return confirm('Are you sure about this deletion?')">Go back to admin page</a>
</div>
</body>
</html>
