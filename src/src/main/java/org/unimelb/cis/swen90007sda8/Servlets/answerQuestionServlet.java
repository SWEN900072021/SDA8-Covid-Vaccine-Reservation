package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;
import org.unimelb.cis.swen90007sda8.Mappers.questionMapper;
import org.unimelb.cis.swen90007sda8.Models.questionModel;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "answerQuestionServlet", value = "/answer_question")
public class answerQuestionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<questionModel> questions = questionMapper.getQuestions();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("answerquestions.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<questionModel> questions = questionMapper.getQuestions();
        String email = SecurityUtils.getSubject().getPrincipals().toString();
        questionMapper.clearOldAnswer(email);
        for(questionModel question:questions){
            Integer questionID = question.getId();
            String answer = request.getParameter(question.getId().toString());
            questionMapper.insertNewAnswer(email,questionID,Boolean.parseBoolean(answer));
        }
        List<String> suitableVaccines =  questionMapper.getSuitableVaccines(email);
        request.setAttribute("suitableVaccines", suitableVaccines);
        request.getRequestDispatcher("/bookvaccination").forward(request,response);
    }
}