package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Mappers.UserMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.*;
import java.sql.ResultSet;

@WebServlet(name = "showCertificationServlet", value = "/showcertification")
public class showCertificationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String email = (String) request.getSession().getAttribute("email");
        if(UserMapper.getVaccinatedByEmail(email)){
            writer.println("<h1>You are vaccinated!"+
                    "<br><a href=\"mainpage.jsp\">Go Back<a>");
        }else{
            writer.println("<h1>You Are Not vaccinated!"+
                    "<br><a href=\"mainpage.jsp\">Go Back<a>");
        }
    }
}