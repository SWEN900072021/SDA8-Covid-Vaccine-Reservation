package com.example.swen90008sda8.Servlets;


import com.example.swen90008sda8.Mappers.TimeSlotMapper;
import com.example.swen90008sda8.Models.bookingModel;
import com.example.swen90008sda8.Models.timeSlotModel;
import com.example.swen90008sda8.Models.userModel;
import com.example.swen90008sda8.UnitOfWork.UnitOfWork;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        HashMap<String, List<timeSlotModel>> context = new HashMap<>();
        TimeSlotMapper timeSlotDB = new TimeSlotMapper();
        UnitOfWork unitOfwork;
        bookingModel booking = new bookingModel(user.getEmail(),id,vname);
        response.sendRedirect("bookvaccination");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
