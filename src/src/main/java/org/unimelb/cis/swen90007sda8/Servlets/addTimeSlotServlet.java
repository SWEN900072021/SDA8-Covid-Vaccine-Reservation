package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Mappers.VaccineMapper;
import org.unimelb.cis.swen90007sda8.Models.hcpModel;
import org.unimelb.cis.swen90007sda8.Models.timeRange;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;
import org.apache.shiro.SecurityUtils;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addTimeSlotServlet", value = "/add_timeslot")
public class addTimeSlotServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<vaccineModel> vaccines = VaccineMapper.getVaccines();
        request.setAttribute("vaccines", vaccines);
        request.getRequestDispatcher("addtimeslot.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        LocalTime fromTime = LocalTime.parse(from);
        LocalTime toTime = LocalTime.parse(to);
        Subject currentUser = SecurityUtils.getSubject();
        String email = currentUser.getPrincipals().toString();
        String provider = new hcpModel(email).getHcpName();
        String numberofshots = request.getParameter("numberofshots");
        PrintWriter writer = response.getWriter();
        timeRange timeRange = new timeRange(date, from, to);
        vaccineModel vaccine = VaccineMapper.find(request.getParameter("vname1"));
        if(toTime.compareTo(fromTime)>0){
            lockManager.getInstance().acquireLock(new ArrayList<String>(Arrays.asList("editingTimeslotBy "+provider)), Thread.currentThread().getName());
            if(TimeSlotMapper.insertTimeSlot(timeRange,provider,numberofshots,vaccine)){
                response.setContentType("text/html");
                writer.println("<h3> Slot "+date+" added!");
            }else{
                response.setContentType("text/html");
                writer.println("<h3>Timeslot existed!");
            }
            lockManager.getInstance().releaseLock(new ArrayList<String>(Arrays.asList("editingTimeslotBy "+provider)), Thread.currentThread().getName());
        }else{
            response.setContentType("text/html");
            writer.println("<h3>Time range wrong!");
        }
    }
}
