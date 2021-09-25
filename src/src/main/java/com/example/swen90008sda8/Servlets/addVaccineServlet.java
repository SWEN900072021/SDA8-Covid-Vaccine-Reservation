package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addVaccineServlet", value = "/add_vaccine")
public class addVaccineServlet extends HttpServlet{
    private String message;
    public void init() {
        message = "Hello World123!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(((String) request.getSession().getAttribute("identity")).equals("Admin")){
            response.sendRedirect("addvaccine.jsp");
        } else{
            PrintWriter writer = response.getWriter();
            writer.println("<h3> You don't have permission!" + (String) request.getSession().getAttribute("identity"));
            return;
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        String name = request.getParameter("vaccinename");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        PrintWriter writer = response.getWriter();
        String s = "INSERT INTO vaccines(name, fromAge, toAge) VALUES (" +"'"+name+"'"+','+"'"+from+"'"+','+"'"+to+"'"+");";
        ResultSet rs = new postgresqlConnector().connect(s);
        writer.println("<h3> Vaccine "+name+" added!");
    }
}
