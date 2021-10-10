package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.TimeRangeMapper;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Mappers.VaccineMapper;
import org.unimelb.cis.swen90007sda8.Models.hcpModel;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;
import org.apache.shiro.SecurityUtils;

import java.io.*;
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
        String email = (String)request.getSession().getAttribute("email");
        String provider = new hcpModel(email).getHcpName();
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
