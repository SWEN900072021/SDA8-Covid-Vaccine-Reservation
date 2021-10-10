package org.unimelb.cis.swen90007sda8.Servlets;


import org.unimelb.cis.swen90007sda8.Mappers.BookingMapper;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Models.*;
import org.unimelb.cis.swen90007sda8.UnitOfWork.bookingUnitOfWork;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.apache.shiro.SecurityUtils;

@SuppressWarnings("InstantiationOfUtilityClass")
@WebServlet(name = "bookServlet", value = "/book")
public class bookServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        userModel user = (userModel) SecurityUtils.getSubject().getSession().getAttribute("user");
        timeSlotModel timeslot = TimeSlotMapper.find(id);
        HashMap<String, List<bookingModel>> context = new HashMap<>();
        BookingMapper bookingDB = new BookingMapper();
        bookingUnitOfWork unitOfwork = new bookingUnitOfWork(context,bookingDB);
        bookingModel booking = new bookingModel(user,timeslot);

        unitOfwork.registerNew(booking);

        unitOfwork.commit();
        
        response.sendRedirect("bookvaccination");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
