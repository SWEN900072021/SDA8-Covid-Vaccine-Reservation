package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.Mappers.hcpMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "setNotVaccinatedServlet", value = "/setnotvaccinated")
public class setNotVaccinatedServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.hasRole("Health Care Provider")) {
            PrintWriter writer = response.getWriter();
            writer.println("You don't have permission to set vaccinated!" +
                    "<br><a href=\"getusers.jsp\">Go Back<a>");
            return;
        }
        String email = request.getParameter("email");
        hcpMapper.setNotVaccinatedByEmail(email);
        response.sendRedirect("get_user");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
