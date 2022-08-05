package com.example.auto_park.servlet;

import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Homepage", value = "/Homepage")
public class Homepage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        boolean showForm = true;
        if(u!=null) {
            showForm = false;
        }
        request.setAttribute("utente", u);
        request.setAttribute("showForm", showForm);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
