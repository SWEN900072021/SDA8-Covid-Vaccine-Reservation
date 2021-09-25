package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "bookServlet", value = "/book")
public class bookServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = (String) request.getParameter("date");
        String from = (String) request.getParameter("from");
        String to = (String) request.getParameter("to");
        String provider = (String) request.getParameter("provider");
        Integer slotId = 0;
        Integer numberOfShots = 0;
        String s = "SELECT id, numberofshots FROM timeslots where date =" + "'"+date+"' And"+ " fromtime = "+ "'" +from+ "' And"+ " totime = '"+to
                + "' And"+ " provider = '"+provider+"';";
        ResultSet rs = new postgresqlConnector().connect(s);
        try {
            if(rs.next()){
                slotId=rs.getInt(1);
                numberOfShots=rs.getInt(2);
            }
            if(numberOfShots>0){
                String email = (String) request.getSession().getAttribute("email");
                String s0 =  "SELECT bookedtimeslot From users WHERE email ="+"'"+email+"';";
                rs = new postgresqlConnector().connect(s0);
                if(rs.next()&&rs.getInt(1)==0) {
                    String s1 = "UPDATE  users  SET bookedtimeslot =" + slotId + "WHERE email =" + "'" + email + "';";
                    new postgresqlConnector().connect(s1);
                    String s2 = "UPDATE  timeslots  SET numberofshots =" + (numberOfShots - 1) + "WHERE id =" + slotId + ";";
                    new postgresqlConnector().connect(s2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(s);

        response.sendRedirect("bookvaccination");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
