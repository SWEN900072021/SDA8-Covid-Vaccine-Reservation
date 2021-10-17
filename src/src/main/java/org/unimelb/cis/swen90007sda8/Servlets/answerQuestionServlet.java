package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Mappers.BookingMapper;
import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;
import org.unimelb.cis.swen90007sda8.Mappers.questionMapper;
import org.unimelb.cis.swen90007sda8.Models.*;
import org.unimelb.cis.swen90007sda8.UnitOfWork.answerUnitOfWork;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
        answerModel anwser = new answerModel(new recipientModel(email));

        HashMap<String, List<answerModel>> context = new HashMap<>();
        questionMapper answerDB = new questionMapper();
        answerUnitOfWork unitOfwork = new answerUnitOfWork(context,answerDB);

        unitOfwork.registerDeleted(anwser);
        for(questionModel question:questions){
            answerModel newAnwser = new answerModel(new recipientModel(email),question
                    , Boolean.parseBoolean(request.getParameter(question.getId().toString())));
            unitOfwork.registerNew(newAnwser);
        }
        unitOfwork.commit();

        List<String> suitableVaccines =  questionMapper.getSuitableVaccines(email);
        request.getSession().setAttribute("suitableVaccines", suitableVaccines);
        request.getRequestDispatcher("/bookvaccination").forward(request,response);
    }
}