package com.example.auto_park.servlet.prenotazione;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "VisualizzaAllPrenotazioni", value = "/VisualizzaAllPrenotazioni")
public class VisualizzaAllPrenotazioni extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrenotazioneDAO pd = new PrenotazioneDAO();
        UtenteDAO ud = new UtenteDAO();
        List<Prenotazione> prenotazioni = new ArrayList<>();
        prenotazioni = pd.getPrenotazioni();
        request.setAttribute("lp",prenotazioni);
        request.getRequestDispatcher("visualizzaAllPrenotazioni.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
