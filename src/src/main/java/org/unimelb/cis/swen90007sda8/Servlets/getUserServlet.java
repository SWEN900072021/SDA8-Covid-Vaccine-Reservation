package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.UserMapper;
import org.unimelb.cis.swen90007sda8.Mappers.VaccineMapper;
import org.unimelb.cis.swen90007sda8.Models.userModel;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.io.*;
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
        List<vaccineModel> vaccines = VaccineMapper.getVaccines();
        request.setAttribute("vaccines", vaccines);
        request.setAttribute("users", users);
        request.setAttribute("viewing", "All users");
        if(((String)request.getSession().getAttribute("identity")).equals("Admin")){
            request.getRequestDispatcher("getplainusers.jsp").forward(request,response);
        }else if(((String)request.getSession().getAttribute("identity")).equals("Health Care Provider")){
            request.getRequestDispatcher("getusers.jsp").forward(request,response);
        }else{
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String vaccineName = request.getParameter("vname");
        List<userModel> users = UserMapper.findWithVaccineName(vaccineName);
        List<vaccineModel> vaccines = VaccineMapper.getVaccines();
        request.setAttribute("vaccines", vaccines);
        request.setAttribute("users", users);
        request.setAttribute("viewing", vaccineName);
        if(((String)request.getSession().getAttribute("identity")).equals("Admin")){
            request.getRequestDispatcher("getplainusers.jsp").forward(request,response);
        }else if(((String)request.getSession().getAttribute("identity")).equals("Health Care Provider")){
            request.getRequestDispatcher("getusers.jsp").forward(request,response);
        }else{
        }
    }
}
