<%@ page import="java.util.ArrayList" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.questionModel" %>
<%@ page import="org.unimelb.cis.swen90007sda8.Models.vaccineModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .table {
        margin-left: auto;
        margin-right: auto;
    }
</style>
<head>
    <title>Questions</title>
</head>
<body>
<%
    ArrayList questions = (ArrayList) request.getAttribute("questions");
%>
<table class="table" style="padding-left: 10px">
    <tr style="background-color: dimgrey">
        <th>Question ID</th>
        <th>Question Body</th>
    </tr>

    <%
        for(int i = 0;i<questions.size();i++){
            questionModel question =(questionModel) questions.get(i);%>
    <tr>
        <td><%=question.getQuestions() %></br>
            <input name="answer" type="radio" value="true" checked="checked">True
            <input name="answer" type="radio" value="false">False
        </td>

    </tr>
    <% }
    %>

</table>
</body>
</html>
