package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.Mappers.UserMapper;
import com.example.swen90008sda8.Models.userModel;

import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "logInServlet", value = "/login")
public class logInServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        System.out.println("Hello from GET method in logInServlet");
        String user = request.getParameter("email");
        String pass = request.getParameter("passWord");
        PrintWriter writer = response.getWriter();
        userModel currentUser = UserMapper.findUser(user,pass);
        if(currentUser == null){
            writer.println("Email or password isn't correct!");
        }else{
            request.getSession().setAttribute("email", user);
            request.getSession().setAttribute("identity", currentUser.getIdentity());
            request.getSession().setAttribute("hcpname", currentUser.getHcpName());
            String identity = currentUser.getIdentity();
            if(identity.equals("Admin")){
                    response.sendRedirect("adminpage.jsp");
            }else if(identity.equals("Health Care Provider")) {
                response.sendRedirect("hcppage.jsp");
            }else{
                response.sendRedirect("mainpage.jsp");
            }
        }
    }
}
