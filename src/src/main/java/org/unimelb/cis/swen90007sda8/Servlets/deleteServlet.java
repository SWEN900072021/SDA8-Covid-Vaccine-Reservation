package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "deleteServlet", value = "/delete")
public class deleteServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = (String) request.getParameter("date");
        String from = (String) request.getParameter("from");
        String to = (String) request.getParameter("to");
        String provider = (String) request.getParameter("provider");
        try {
            TimeSlotMapper.deleteTimeSlotByDetails(date,from,to,provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("get_timeslot");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
