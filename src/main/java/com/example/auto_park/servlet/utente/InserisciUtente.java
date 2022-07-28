package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.entity.utente.Utente;
import com.example.auto_park.hibernate.entity.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "InserisciUtente", value = "/InserisciUtente")
public class InserisciUtente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String data_nascita_string = request.getParameter("data_nascita");
        Date data_nascita = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            data_nascita = sdf.parse(data_nascita_string);
        }
        catch(Exception e) {
            System.out.println("Impossibile prelevare la data");
        }

        String tipo = request.getParameter("tipo");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utente u = new Utente(0L,nome,cognome,data_nascita,tipo,username,password,null);
        System.out.println("Utente: " + u);
        UtenteDAO ud = new UtenteDAO();
        ud.inserisciUtente(u);
        System.out.println("Aggiunto");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Complimenti " + nome + " " + cognome+  " sei stato aggiunto!" + "</h1>");
        out.println("</body></html>");

    }
}
