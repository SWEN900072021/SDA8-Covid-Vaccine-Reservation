package com.example.swen90008sda8;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "logInServlet", value = "/login")
public class logInServlet extends HttpServlet{
    private String message;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        System.out.println("Hello from GET method in logInServlet");
        String user = request.getParameter("email");
        String pass = request.getParameter("passWord");
        PrintWriter writer = response.getWriter();
        String s = "SELECT email,password,user_identity,hcpname FROM users where email =" + "'" +user + "'" + " AND password = "+ "'" +pass+ "';";
        ResultSet rs = new postgresqlConnector().connect(s);
        try {
            if (rs.next() == false) {
                writer.println("Email or password isn't correct!");
            } else {
                request.getSession().setAttribute("email", user);
                request.getSession().setAttribute("identity", rs.getString(3));
                request.getSession().setAttribute("hcpname", rs.getString(4));
                writer.println("Welcome!" + rs.getString(1));
                if(rs.getString(3).equals("Admin")){
                    response.sendRedirect("adminpage.jsp");
                }else if(rs.getString(3).equals("Health Care Provider")) {
                    response.sendRedirect("hcppage.jsp");
                }else{
                    response.sendRedirect("mainpage.jsp");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
