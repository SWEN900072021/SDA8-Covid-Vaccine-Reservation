package com.example.swen90008sda8;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addTimeSlotServlet", value = "/add_timeslot")
public class addTimeSlotServlet extends HttpServlet{
    private String message;
    public void init() {
        message = "Hello World123!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(((String) request.getSession().getAttribute("identity")).equals("Health Care Provider")){
            response.sendRedirect("addtimeslot.jsp");
        } else{
            PrintWriter writer = response.getWriter();
            writer.println("<h3> You don't have permission!" + (String) request.getSession().getAttribute("identity"));
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        PrintWriter writer = response.getWriter();
        String s = "INSERT INTO timeslots(date, fromTime, toTime) VALUES (" +"'"+date+"'"+','+"'"+from+"'"+','+"'"+to+"'"+");";
        ResultSet rs = new postgresqlConnector().connect(s);
        writer.println("<h3> Slot "+date+" added!");
    }
}
