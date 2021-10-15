package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.util.ByteSource;
import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;

import java.io.*;
import java.sql.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "signUpProviderServlet", value = "/signupprovider")
public class signUpProviderServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("signupprovider.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        String user = request.getParameter("email");
        String pass = request.getParameter("passWord");
        String identity = request.getParameter("identity");
        String post = request.getParameter("postcode");
        String top = request.getParameter("typeOfProvider");
        String hcpname = request.getParameter("hcpName");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        HashRequest hashRequest = new SimpleHashRequest("SHA-256", ByteSource.Util.bytes(pass), ByteSource.Util.bytes(user), 500000);
        DefaultHashService hashService = new DefaultHashService();
        Hash hash = hashService.computeHash(hashRequest);
        Shiro1CryptFormat format = new Shiro1CryptFormat();
        String passHashed = format.format(hash);

        try {
            if(adminMapper.insertNewProvider(user,passHashed,identity,post,top,hcpname)){
                writer.println("<h3>Registion Complete!");
            }else{
                writer.println("<h3> User Existed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
