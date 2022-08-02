package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ModificaUtente", value = "/ModificaUtente")
public class ModificaUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascitaString = request.getParameter("dataNascita");
        Date dataNascita = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dataNascita = (Date)  sdf.parse(dataNascitaString);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }

        String tipo = "customer";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utente u = new Utente( id, nome, cognome, dataNascita, tipo, username, password, null);
        System.out.println("Utente: " + u);
        UtenteDAO ud = new UtenteDAO();
        ud.saveOrUpdateUtente(u);
        System.out.println("Aggiornato");
        request.getRequestDispatcher("successo.jsp").forward(request,response);
    }
}
