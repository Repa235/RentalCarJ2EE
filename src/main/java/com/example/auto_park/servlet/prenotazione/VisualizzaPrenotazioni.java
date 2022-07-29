package com.example.auto_park.servlet.prenotazione;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "VisualizzaPrenotazioni", value = "/VisualizzaPrenotazioni")
public class VisualizzaPrenotazioni extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrenotazioneDAO pd = new PrenotazioneDAO();
        List<Prenotazione> lp = pd.getPrenotazioni();
        System.out.println("Visualizza prenotazioni: "+lp);
        request.setAttribute("lp",lp);
        request.getRequestDispatcher("visualizzaPrenotazioni.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
