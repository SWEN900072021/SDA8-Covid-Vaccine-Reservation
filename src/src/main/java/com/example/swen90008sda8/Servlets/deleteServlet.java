package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "deleteServlet", value = "/delete")
public class deleteServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = (String) request.getParameter("date");
        String from = (String) request.getParameter("from");
        String to = (String) request.getParameter("to");
        String provider = (String) request.getParameter("provider");
        String s = "Delete FROM timeslots where date =" + "'"+date+"' And"+ " fromtime = "+ "'" +from+ "' And"+ " totime = '"+to
                + "' And"+ " provider = '"+provider+"';";
        System.out.println(s);
        ResultSet rs = new postgresqlConnector().connect(s);
        response.sendRedirect("get_timeslot");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
