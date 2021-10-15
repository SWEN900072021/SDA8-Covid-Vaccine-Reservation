package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Mappers.*;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addQuestionServlet", value = "/add_question")
public class addQuestionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<vaccineModel> vaccines = VaccineMapper.getVaccines();
        request.setAttribute("vaccines", vaccines);
        request.getRequestDispatcher("addquestions.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String vname = request.getParameter("vname1");
        String questionBody = request.getParameter("questionBody");
        Boolean answer = Boolean.parseBoolean(request.getParameter("answer"));
        adminMapper.insertQuestion(vname, questionBody, answer);
        response.setContentType("text/html");
        writer.println("<h3> Question added!");
    }
}
