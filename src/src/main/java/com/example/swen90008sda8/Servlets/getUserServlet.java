package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.Models.userModel;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "getUserServlet", value = "/get_user")
public class getUserServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String s = "";
        try {
            if (request.getSession().getAttribute("identity").equals("Recipient")) {
                PrintWriter writer = response.getWriter();
                writer.println("<h3> You don't have permission!" + request.getSession().getAttribute("identity"));
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if(request.getParameter("vaccinated") ==null){
            if (request.getSession().getAttribute("identity").equals("Admin")){
                s = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE email != 'admin@gmail.com';";
            }else {
                s = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE user_identity = 'Recipient' AND email != 'admin@gmail.com';";
            }
        }
        else if(request.getParameter("vaccinated").equals("True")){
            if (request.getSession().getAttribute("identity").equals("Admin")){
                s = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE vaccinated = True;";
            }else {
                s = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE user_identity = 'Recipient' AND vaccinated = True;";
            }
        }
        else{
            if (request.getSession().getAttribute("identity").equals("Admin")){
                s = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE vaccinated = False;";
            }else {
                s = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE user_identity = 'Recipient' AND vaccinated = False;";
            }
        }

        ResultSet rs = new postgresqlConnector().connect(s);
        List<userModel> users = new ArrayList<>();
        try {
            while (rs.next()) {
                userModel user = new userModel();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDate(rs.getDate("dateofbirth"));
                user.setIdentity(rs.getString("user_identity"));
                user.setVaccinated(rs.getBoolean("vaccinated"));
                user.setTimeslotID(rs.getInt("bookedtimeslot"));
                users.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("getusers.jsp").forward(request,response);
        PrintWriter writer = response.getWriter();
        writer.println(users);
        System.out.println(users);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
