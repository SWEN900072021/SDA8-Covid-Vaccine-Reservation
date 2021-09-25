package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Mappers.UserMapper;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "setNotVaccinatedServlet", value = "/setnotvaccinated")
public class setNotVaccinatedServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!((String) request.getSession().getAttribute("identity")).equals("Health Care Provider")) {
            PrintWriter writer = response.getWriter();
            writer.println("You don't have permission to set vaccinated!" + (String) request.getSession().getAttribute("identity"));
            return;
        }
        String email = (String) request.getParameter("email");
        UserMapper.setNotVaccinatedByEmail(email);
        response.sendRedirect("get_user");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
