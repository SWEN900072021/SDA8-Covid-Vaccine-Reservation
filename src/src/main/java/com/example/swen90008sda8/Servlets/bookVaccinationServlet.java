package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.Mappers.TimeSlotMapper;
import com.example.swen90008sda8.Models.timeSlotModel;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        List<timeSlotModel> timeslots = TimeSlotMapper.getTimeSlots();
        request.setAttribute("timeslots", timeslots);
        request.getRequestDispatcher("bookVaccination.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
