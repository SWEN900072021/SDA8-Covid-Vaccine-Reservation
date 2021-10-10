package org.unimelb.cis.swen90007sda8.Servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.unimelb.cis.swen90007sda8.Mappers.*;
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
        String identity = ((userModel) SecurityUtils.getSubject().getSession().getAttribute("user")).getIdentity();
        String vaccinated = request.getParameter("vaccinated");
        Subject currentUser = SecurityUtils.getSubject();
        List<userModel> users = adminMapper.findWithVaccinated(vaccinated, identity);
        List<vaccineModel> vaccines = VaccineMapper.getVaccines();
        request.setAttribute("vaccines", vaccines);
        request.setAttribute("users", users);
        request.setAttribute("viewing", "All users");
        if(currentUser.hasRole("Admin")){
            request.getRequestDispatcher("getplainusers.jsp").forward(request,response);
        }else if(currentUser.hasRole("Health Care Provider")){
            request.getRequestDispatcher("getusers.jsp").forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        vaccineModel vaccineName = new vaccineModel(request.getParameter("vname"));
        List<userModel> users = adminMapper.findWithVaccineName(vaccineName);
        List<vaccineModel> vaccines = VaccineMapper.getVaccines();
        Subject currentUser = SecurityUtils.getSubject();
        request.setAttribute("vaccines", vaccines);
        request.setAttribute("users", users);
        request.setAttribute("viewing", vaccineName.getName());
        if(currentUser.hasRole("Admin")){
            request.getRequestDispatcher("getplainusers.jsp").forward(request,response);
        }else if(currentUser.hasRole("Health Care Provider")){
            request.getRequestDispatcher("getusers.jsp").forward(request,response);
        }
    }
}
