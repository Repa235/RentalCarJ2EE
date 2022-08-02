package com.example.auto_park.servlet.prenotazione;

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

@WebServlet(name = "ModificaPrenotazione", value = "/ModificaPrenotazione")
public class ModificaPrenotazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idPrenotazione = Long.parseLong(request.getParameter("idPrenotazione"));
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
        PrenotazioneDAO pd = new PrenotazioneDAO();
        VeicoloDAO vd = new VeicoloDAO();
        Veicolo v = vd.getVeicolo(new Veicolo(idVeicolo));
        Prenotazione p = pd.getPrenotazione(new Prenotazione(idPrenotazione));
        p.setDataInizio(dataInizio);
        p.setDataFine(dataFine);

        pd.saveOrUpdatePrenotazione(p);
        request.getRequestDispatcher("successo.jsp").forward(request,response);
    }
}
