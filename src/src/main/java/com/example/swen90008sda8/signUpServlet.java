package com.example.swen90008sda8;

import java.io.*;
import java.sql.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "SignupServlet", value = "/signup")
public class signUpServlet extends HttpServlet{
    private String message;
    public void init() {
        message = "Hello Signup!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("signup.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        System.out.println("Hello from GET method in signupServlet");
        String user = request.getParameter("email");
        String pass = request.getParameter("passWord");
        String date = request.getParameter("dateOfBirth");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String identity = request.getParameter("identity");
        String post = request.getParameter("postcode");
        String top = request.getParameter("typeOfProvider");
        PrintWriter writer = response.getWriter();
        String s = "INSERT INTO users(email, password, dateofbirth, firstname, lastname, user_identity, postcode," +
                " typeofprovider) VALUES (" +"'"+user+"'"+','+"'"+pass+"'"+','+"'"+date+"'"+','+"'"+ firstName+"'"+','
                +"'"+lastName+"'"+','+"'"+identity+"'"+','+"'"+post+"'"+','+"'"+top+"'"+ ");";
        ResultSet rs = new postgresqlConnector().connect(s);
        writer.println("<h3> Welcome,"+firstName+"!");
    }
}
