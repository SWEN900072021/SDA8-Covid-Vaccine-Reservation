package com.example.swen90008sda8;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.ServletException;
        import javax.servlet.annotation.*;
        import javax.servlet.http.*;
import javax.xml.transform.Result;

@WebServlet(name = "showCertificationServlet", value = "/showcertification")
public class showCertificationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = (String) request.getSession().getAttribute("email");
        String s = "SELECT vaccinated FROM users WHERE email = '" + email + "';";
        ResultSet rs = new postgresqlConnector().connect(s);
        try {
            if (rs.next()) {
                PrintWriter writer = response.getWriter();
                Boolean vaccinated = rs.getBoolean(1);
                writer.println(vaccinated);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}