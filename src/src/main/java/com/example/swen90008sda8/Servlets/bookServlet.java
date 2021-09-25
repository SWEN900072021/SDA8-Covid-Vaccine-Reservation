package com.example.swen90008sda8.Servlets;


import com.example.swen90008sda8.UnitOfWork.UnitOfWork;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "bookServlet", value = "/book")
public class bookServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String date = (String) request.getParameter("date");
        String from = (String) request.getParameter("from");
        String to = (String) request.getParameter("to");
        String provider = (String) request.getParameter("provider");
        String email = (String) request.getSession().getAttribute("email");
        try {
            UnitOfWork.bookTimeSlot(email,date,from,to,provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("bookvaccination");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
