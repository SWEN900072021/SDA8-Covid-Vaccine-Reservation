package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Models.timeRange;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "deleteServlet", value = "/delete")
public class deleteServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String provider = request.getParameter("provider");
        Subject currentUser = SecurityUtils.getSubject();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            if(currentUser.hasRole("Health Care Provider")){
                timeRange timeRange = new timeRange(date,from,to);
                Integer timeslotID = TimeSlotMapper.getIdByDetails(timeRange,provider);
                lockManager.getInstance().acquireLock(new ArrayList<String>(Arrays.asList("timeslot "+timeslotID)), Thread.currentThread().getName());
                TimeSlotMapper.deleteTimeSlotByDetails(timeRange,provider);
                lockManager.getInstance().releaseLock(new ArrayList<String>(Arrays.asList("timeslot "+timeslotID)), Thread.currentThread().getName());
                response.sendRedirect("get_timeslot");
            }else{
                writer.println("<h3>You can't delete timeslots");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
