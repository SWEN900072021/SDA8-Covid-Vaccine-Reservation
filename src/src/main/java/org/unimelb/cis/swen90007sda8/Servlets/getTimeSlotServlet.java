package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;
import org.unimelb.cis.swen90007sda8.Models.*;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "getTimeSlotServlet", value = "/get_timeslot")
public class getTimeSlotServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String identity = adminMapper.find(SecurityUtils.getSubject().getPrincipals().toString()).getIdentity();
        String email = SecurityUtils.getSubject().getPrincipals().toString();
        String hcpname = new hcpModel(email).getHcpName();
        System.out.println(email);
        List<timeSlotModel> timeslots = TimeSlotMapper.getTimeSlotsByDetails(identity,hcpname);
        request.setAttribute("timeslots", timeslots);
        request.getRequestDispatcher("gettimeslot.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
