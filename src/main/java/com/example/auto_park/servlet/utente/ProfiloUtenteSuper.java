package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfiloUtenteSuper", value = "/ProfiloUtenteSuper")
public class ProfiloUtenteSuper extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente superUser = (Utente) session.getAttribute("utente");
        System.out.println("Utente: " + superUser.getNome() +" "+ superUser.getCognome());
        UtenteDAO ud = new UtenteDAO();
        List<Utente> clienti = ud.getCustomers();
        request.setAttribute("superUser", superUser);
        request.setAttribute("clienti", clienti);
        System.out.println("Clienti:" + clienti);
        request.getRequestDispatcher("profiloSuper.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
