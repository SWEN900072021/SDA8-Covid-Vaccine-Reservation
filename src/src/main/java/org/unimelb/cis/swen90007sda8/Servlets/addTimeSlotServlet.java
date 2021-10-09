package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.TimeRangeMapper;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Mappers.VaccineMapper;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "addTimeSlotServlet", value = "/add_timeslot")
public class addTimeSlotServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("identity").equals("Health Care Provider")){
            List<vaccineModel> vaccines = VaccineMapper.getVaccines();
            request.setAttribute("vaccines", vaccines);
            request.getRequestDispatcher("addtimeslot.jsp").forward(request,response);
        } else{
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<h3> You don't have permission!" + request.getSession().getAttribute("identity"));
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String provider = (String)request.getSession().getAttribute("hcpname");
        String numberofshots = request.getParameter("numberofshots");
        PrintWriter writer = response.getWriter();
        Integer timeid = TimeRangeMapper.insertTimeRange(date, from, to);
        vaccineModel vaccine = VaccineMapper.find(request.getParameter("vname1"));
        if(timeid!=null && timeid>=0){
            TimeSlotMapper.insertTimeSlot(timeid,provider,numberofshots,vaccine);
            response.setContentType("text/html");
            writer.println("<h3> Slot "+date+" added!" +
                    "<br><a href=\"hcppage.jsp\">Go Back<a>");
        }else{
            response.setContentType("text/html");
            writer.println("Time range wrong!" +
                    "<br><a href=\"hcppage.jsp\">Go Back<a>");
        }
    }
}
