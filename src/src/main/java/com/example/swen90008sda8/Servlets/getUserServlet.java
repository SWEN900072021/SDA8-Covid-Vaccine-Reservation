package com.example.swen90008sda8.Servlets;

import com.example.swen90008sda8.Mappers.UserMapper;
import com.example.swen90008sda8.Models.userModel;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Mappers.UserMapper.*;
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
        String identity = (String) request.getSession().getAttribute("identity");
        String vaccinated = request.getParameter("vaccinated");
        List<userModel> users = UserMapper.findWithVaccinated(vaccinated, identity);

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
