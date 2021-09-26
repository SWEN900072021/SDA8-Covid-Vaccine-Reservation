package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.UserMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "setNotVaccinatedServlet", value = "/setnotvaccinated")
public class setNotVaccinatedServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        if (!request.getSession().getAttribute("identity").equals("Health Care Provider")) {
            PrintWriter writer = response.getWriter();
            writer.println("You don't have permission to set vaccinated!" + request.getSession().getAttribute("identity"));
            return;
        }
        String email = request.getParameter("email");
        UserMapper.setNotVaccinatedByEmail(email);
        response.sendRedirect("get_user");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
