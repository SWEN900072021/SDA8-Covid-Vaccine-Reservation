package org.unimelb.cis.swen90007sda8.Servlets;


import org.unimelb.cis.swen90007sda8.Mappers.BookingMapper;
import org.unimelb.cis.swen90007sda8.Models.bookingModel;
import org.unimelb.cis.swen90007sda8.Models.userModel;
import org.unimelb.cis.swen90007sda8.UnitOfWork.UnitOfWork;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "bookServlet", value = "/book")
public class bookServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        String vname = request.getParameter("name");
        userModel user = (userModel) request.getSession().getAttribute("user");
        HashMap<String, List<bookingModel>> context = new HashMap<>();
        BookingMapper bookingDB = new BookingMapper();
        UnitOfWork unitOfwork = new UnitOfWork(context,bookingDB);
        bookingModel booking = new bookingModel(user.getEmail(),id,vname);

        unitOfwork.registerNew(booking);

        unitOfwork.commit();
        
        response.sendRedirect("bookvaccination");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
