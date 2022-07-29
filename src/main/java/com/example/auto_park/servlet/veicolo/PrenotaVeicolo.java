package com.example.auto_park.servlet.veicolo;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "PrenotaVeicolo", value = "/PrenotaVeicolo")
public class PrenotaVeicolo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idUtente = Long.parseLong(request.getParameter("idUtente"));
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        Date dataInizio = null;
        Date dataFine = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dataInizio = sdf.parse(dataInizioString);
            dataFine = sdf.parse(dataFineString);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }
        Long idVeicolo = Long.parseLong(request.getParameter("veicolo"));

        VeicoloDAO vd = new VeicoloDAO();
        Veicolo v = vd.getVeicolo(new Veicolo(idVeicolo));
        UtenteDAO ud = new UtenteDAO();
        Utente u = ud.getUtente(new Utente(idUtente));
        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione p = new Prenotazione(u,v,dataInizio,dataFine,false);
        pd.saveOrUpdatePrenotazione(p);
        request.getRequestDispatcher("successo.jsp").forward(request,response);
    }
}
