package com.example.auto_park.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Errore", value = "/Errore")
public class Errore extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errore = request.getParameter("err");
        request.setAttribute("errore",errore);
        request.getRequestDispatcher("errore.jsp").forward(request, response);
    }
}
