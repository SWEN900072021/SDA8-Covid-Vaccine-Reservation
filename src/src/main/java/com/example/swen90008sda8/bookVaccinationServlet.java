package com.example.swen90008sda8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "bookVaccinationServlet", value = "/bookvaccination")
public class bookVaccinationServlet extends HttpServlet {
    private String message;
    public void init() {
        message = "Hello World123!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String s = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots;";
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
        request.getRequestDispatcher("bookVaccination.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
