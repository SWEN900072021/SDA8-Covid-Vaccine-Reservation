package com.example.swen90008sda8;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "setVaccinatedServlet", value = "/setvaccinated")
public class setVaccinatedServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!((String) request.getSession().getAttribute("identity")).equals("Health Care Provider")) {
            PrintWriter writer = response.getWriter();
            writer.println("<h3> You don't have permission to set vaccinated!" + (String) request.getSession().getAttribute("identity"));
            return;
        }
        String email = (String) request.getParameter("email");
        String s = "UPDATE  users  SET vaccinated = True WHERE email ="+"'"+ email+"';";
        System.out.println(s);
        ResultSet rs = new postgresqlConnector().connect(s);
        response.sendRedirect("get_user");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
