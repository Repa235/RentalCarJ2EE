package com.example.auto_park.servlet.prenotazione;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@WebServlet(name = "VisualizzaPrenotazioni", value = "/VisualizzaPrenotazioni")
public class VisualizzaPrenotazioni extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrenotazioneDAO pd = new PrenotazioneDAO();
        UtenteDAO ud = new UtenteDAO();
        long id = Long.parseLong(request.getParameter("id"));
        Utente u = ud.getUtente(new Utente(id));
       // List<Prenotazione> lp = pd.getPrenotazioniByUtente(u);
        Set<Prenotazione> lp = u.getPrenotazioni();
        System.out.println("Visualizza prenotazioni: "+lp);
        request.setAttribute("lp",lp);
        request.getRequestDispatcher("visualizzaPrenotazioni.jsp").forward(request,response);
    }
}
