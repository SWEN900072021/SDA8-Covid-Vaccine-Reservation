package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addTimeSlotServlet", value = "/add_timeslot")
public class addTimeSlotServlet extends HttpServlet{
    private String message;
    public void init() {
        message = "Hello World123!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if((request.getSession().getAttribute("identity")!=null)&&((String) request.getSession().getAttribute("identity")).equals("Health Care Provider")){
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
        String provider = (String)request.getSession().getAttribute("hcpname");
        String numberofshots = request.getParameter("numberofshots");
        String vname = request.getParameter("vname");
        PrintWriter writer = response.getWriter();
        String s = "INSERT INTO timeslots(date, fromTime, toTime, provider, numberofshots, vaccineName) VALUES (" +"'"+date+"'"+','+"'"+from+"'"
                +','+"'"+to+"'"+','+"'"+provider+"', "+numberofshots+", '"+vname+ "');";
        ResultSet rs = new postgresqlConnector().connect(s);
        writer.println("<h3> Slot "+date+" added!");
    }
}
