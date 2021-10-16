package org.unimelb.cis.swen90007sda8.Servlets;


import org.unimelb.cis.swen90007sda8.Mappers.BookingMapper;
import org.unimelb.cis.swen90007sda8.Mappers.TimeSlotMapper;
import org.unimelb.cis.swen90007sda8.Models.*;
import org.unimelb.cis.swen90007sda8.UnitOfWork.bookingUnitOfWork;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
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
        Integer timeslotID = Integer.parseInt(request.getParameter("id"));
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        userModel user = (userModel) SecurityUtils.getSubject().getSession().getAttribute("user");
        timeSlotModel timeslot = TimeSlotMapper.find(timeslotID);
        HashMap<String, List<bookingModel>> context = new HashMap<>();
        BookingMapper bookingDB = new BookingMapper();
        bookingUnitOfWork unitOfwork = new bookingUnitOfWork(context,bookingDB);

        lockManager.getInstance().acquireLock("booking "+timeslotID, Thread.currentThread().getName());

        bookingModel booking = new bookingModel(user,timeslot);
        unitOfwork.registerNew(booking);
        unitOfwork.commit();

        lockManager.getInstance().releaseLock("booking "+timeslotID, Thread.currentThread().getName());

        if(bookingDB.find(SecurityUtils.getSubject().getPrincipals().toString())){
            writer.println("<h3>Book success!");
        }else{
            writer.println("<h3>No available shots");
        }
        response.sendRedirect("bookvaccination");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
