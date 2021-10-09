package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;
import org.unimelb.cis.swen90007sda8.Models.*;

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
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        userModel currentUser = adminMapper.findUser(user,pass);
        if(currentUser == null){
            writer.println("Email or password isn't correct!"+
                    "<br><a href=\"index.jsp\">Go Back<a>");
        }else{
            request.getSession().setAttribute("email", user);
            request.getSession().setAttribute("identity", currentUser.getIdentity());
            if(currentUser.getIdentity()=="Health Care Provider"){
                currentUser = new hcpModel(user);
            }
            else if(currentUser.getIdentity()=="Admin"){
                currentUser = new adminModel(user);
            }else{
                currentUser = new recipientModel(user);
            }
            request.getSession().setAttribute("user", currentUser);
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
