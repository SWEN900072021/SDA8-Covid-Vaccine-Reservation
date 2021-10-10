package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.Mappers.TimeRangeMapper;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;

import java.io.*;
import java.sql.SQLException;
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
                Integer timeid = TimeRangeMapper.getIdByDetail(date,from,to);
                TimeSlotMapper.deleteTimeSlotByDetails(timeid,provider);
                response.sendRedirect("get_timeslot");
            }else{
                writer.println("<h3>You can't delete timeslots<br>" +
                        "<br><a href=\"get_timeslot\">Go Back<a>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
