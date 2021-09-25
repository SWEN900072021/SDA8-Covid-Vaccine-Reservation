package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.Models.timeSlotModel;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "getTimeSlotServlet", value = "/get_timeslot")
public class getTimeSlotServlet extends HttpServlet{
    private String message;
    public void init() {
        message = "Hello World123!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String s="";
        try {
            if (((String) request.getSession().getAttribute("identity")).equals("Recipient")) {
                PrintWriter writer = response.getWriter();
                writer.println("<h3> You don't have permission to view all timeslots!" + (String) request.getSession().getAttribute("identity"));
                return;
            }
            else if(((String) request.getSession().getAttribute("identity")).equals("Health Care Provider")){
                s = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots"+" WHERE provider="
                        +"'"+(String)request.getSession().getAttribute("hcpname")+"';";
            }
            else{
                s = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots;";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(s);
        ResultSet rs = new postgresqlConnector().connect(s);
        List<timeSlotModel> timeslots = new ArrayList<>();
        try {
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setDate(rs.getDate("date"));
                timeSlot.setFrom(rs.getTime("fromtime"));
                timeSlot.setTo(rs.getTime("totime"));
                timeSlot.setProvider(rs.getString("provider"));
                timeSlot.setNumberofshots(rs.getInt("numberofshots"));
                timeslots.add(timeSlot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        request.setAttribute("timeslots", timeslots);
        request.getRequestDispatcher("gettimeslot.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
