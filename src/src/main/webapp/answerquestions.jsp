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
<form action="answer_question" method="post" >
    <table cellpadding="5">
        <tr>
            <td>
                <%
                    for(int i = 0;i<questions.size();i++){
                        questionModel question =(questionModel) questions.get(i);%>

                Question<%=i+1%>: <%=question.getQuestions()%>
                <input name=<%=question.getId()%> type="radio" value="true" checked="checked">True
                <input name=<%=question.getId()%> type="radio" value="false">False</br>
                    <% }
    %>
            </td>
        </tr>
    </table>

    <input type = "submit" value = "Submit">
</form>
</body>
</html>
