package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RichiediModificaProfilo", value = "/RichiediModificaProfilo")
public class RichiediModificaProfilo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        UtenteDAO ud = new UtenteDAO();
        Utente u = (Utente) s.getAttribute("utente");
        request.setAttribute("utente",u);
        request.getRequestDispatcher("formModificaUtente.jsp").forward(request,response);
    }
}
