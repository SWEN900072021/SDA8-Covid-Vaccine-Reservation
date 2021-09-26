package org.unimelb.cis.swen90007sda8.Servlets;

import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "signOutServlet", value = "/signout")
public class signOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("identity");
        response.sendRedirect("index.jsp");
    }
}