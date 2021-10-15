package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.Mappers.VaccineMapper;

import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addVaccineServlet", value = "/add_vaccine")
public class addVaccineServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("addvaccine.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        String name = request.getParameter("vaccinename");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        if(VaccineMapper.insertVaccine(name,from,to)){
            writer.println("<h3> Vaccine "+name+" added!");
        }
        else{
            writer.println("<h3> Age range is wrong Or Vaccine existed!");
        }
    }
}
