package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Models.timeSlotModel;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "getTimeSlotServlet", value = "/get_timeslot")
public class getTimeSlotServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String identity = (String) request.getSession().getAttribute("identity");
        String hcpname = (String)request.getSession().getAttribute("hcpname");
        List<timeSlotModel> timeslots = TimeSlotMapper.getTimeSlotsByDetails(identity,hcpname);
        request.setAttribute("timeslots", timeslots);
        request.getRequestDispatcher("gettimeslot.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}