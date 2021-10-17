package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;
import org.unimelb.cis.swen90007sda8.Models.*;

import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "logInServlet", value = "")
public class logInServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.hasRole("Admin")){
            response.sendRedirect("adminpage.jsp");
        }else if(currentUser.hasRole("Health Care Provider")) {
            response.sendRedirect("hcppage.jsp");
        }else if(currentUser.hasRole("Recipient")){
            response.sendRedirect("mainpage.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.hasRole("Admin")){
            response.sendRedirect("adminpage.jsp");
        }else if(currentUser.hasRole("Health Care Provider")) {
            response.sendRedirect("hcppage.jsp");
        }else if(currentUser.hasRole("Recipient")){
            response.sendRedirect("mainpage.jsp");
        }
    }
}
