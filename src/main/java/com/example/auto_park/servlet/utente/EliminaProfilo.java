package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EliminaProfilo", value = "/EliminaProfilo")
public class EliminaProfilo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        UtenteDAO ud = new UtenteDAO();
        Utente u = ud.getUtente(new Utente(id));
        ud.deleteUtente(u);
        System.out.println("Elimina");
        request.getRequestDispatcher("eliminato.jsp").forward(request,response);
    }
}
