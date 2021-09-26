package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.UserMapper;

import java.io.*;
import java.sql.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "signUpProviderServlet", value = "/signupprovider")
public class signUpProviderServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("signup.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        System.out.println("Hello from GET method in signupServlet");
        String user = request.getParameter("email");
        String pass = request.getParameter("passWord");
        String identity = request.getParameter("identity");
        String post = request.getParameter("postcode");
        String top = request.getParameter("typeOfProvider");
        String hcpname = request.getParameter("hcpName");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            if(UserMapper.insertNewProvider(user,pass,identity,post,top,hcpname)){
                writer.println("<h3>Registion Complete!<br>" +
                        "<a href=\"adminpage.jsp\">Go Back<a>");
            }else{
                writer.println("<h3> User Existed!" +
                        "<a href=\"adminpage.jsp\">Go Back<a>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}