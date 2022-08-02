package com.example.auto_park.servlet.prenotazione;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ApprovaPrenotazione", value = "/ApprovaPrenotazione")
public class ApprovaPrenotazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean approvated = Boolean.parseBoolean(request.getParameter("approva"));
        long id = Long.parseLong(request.getParameter("idPrenotazione"));
        System.out.println("Id prenotazione: "+id + " " + "esito: " + approvated);
        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione p = pd.getPrenotazione(new Prenotazione(id));
        if(approvated) {
            p.setApprovato(approvated);
            pd.saveOrUpdatePrenotazione(p);
            request.getRequestDispatcher("successo.jsp").forward(request, response);
        }
        else {
            pd.deletePrenotazione(p);
            request.getRequestDispatcher("successo.jsp").forward(request, response);
        }
    }
}
