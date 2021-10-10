package org.unimelb.cis.swen90007sda8.Servlets;

import org.unimelb.cis.swen90007sda8.Mappers.recipientMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.shiro.SecurityUtils;

import java.io.*;

@WebServlet(name = "showCertificationServlet", value = "/showcertification")
public class showCertificationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String email = SecurityUtils.getSubject().getPrincipals().toString();
        if(recipientMapper.getVaccinatedByEmail(email)){
            writer.println("<h1>You are vaccinated!"+
                    "<br><a href=\"mainpage.jsp\">Go Back<a>");
        }else{
            writer.println("<h1>You Are Not vaccinated!"+
                    "<br><a href=\"mainpage.jsp\">Go Back<a>");
        }
    }
}