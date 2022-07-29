package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.dao.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "InserisciUtente", value = "/InserisciUtente")
public class AggiungiUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascitaString = request.getParameter("dataNascita");
        Date dataNascita = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dataNascita = sdf.parse(dataNascitaString);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }

        String tipo = "customer";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utente u = new Utente( nome, cognome, dataNascita, tipo, username, password, null);
        System.out.println("Utente: " + u);
        UtenteDAO ud = new UtenteDAO();
        ud.saveOrUpdateUtente(u);
        System.out.println("Aggiunto");


        request.getRequestDispatcher("successo.jsp").forward(request,response);
    }
}
