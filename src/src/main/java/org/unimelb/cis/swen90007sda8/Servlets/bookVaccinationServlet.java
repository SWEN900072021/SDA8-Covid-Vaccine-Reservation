package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Models.timeSlotModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String postcode = request.getParameter("postcode");
        String hcpname = request.getParameter("hcpname");
        List<timeSlotModel> timeslots = null;
        if(postcode != null){
            timeslots = TimeSlotMapper.getTimeSlotByPostCode(postcode);
            request.setAttribute("timeslots", timeslots);
            request.getRequestDispatcher("bookVaccination.jsp").forward(request,response);
        }else if(hcpname != null){
            timeslots = TimeSlotMapper.getTimeSlotByProvider(hcpname);
            request.setAttribute("timeslots", timeslots);
            request.getRequestDispatcher("bookVaccination.jsp").forward(request,response);
        }else{
            doGet(request,response);
        }
    }
}
