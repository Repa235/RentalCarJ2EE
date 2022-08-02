package com.example.auto_park.servlet;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebServlet(name = "PrenotazioneServlet", value = "/PrenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrenotazioneDAO pd = new PrenotazioneDAO();
        VeicoloDAO vd = new VeicoloDAO();
        UtenteDAO ud = new UtenteDAO();
        String comando = request.getParameter("comando");
        Prenotazione p = new Prenotazione();
        Veicolo v = new Veicolo();
        Utente u = new Utente();

        Long idPrenotazione = 0L;
        Long idUtente = 0L;
        Long idVeicolo = 0L;

        switch(comando) {
            case "richiediModificaPrenotazione":
                idPrenotazione = Long.parseLong(request.getParameter("id"));
                p = pd.getPrenotazione(new Prenotazione(idPrenotazione));
                List<Veicolo> veicoli = vd.getVeicoli();
                request.setAttribute("prenotazione", p);
                request.setAttribute("veicoli", veicoli);
                request.getRequestDispatcher("formModificaPrenotazione.jsp").forward(request,response);
                break;
            case "richiediPrenotazioneVeicolo":
                u = (Utente) session.getAttribute("utente");
                request.setAttribute("utente",u);
                List<Veicolo> lv = new ArrayList<>();
                lv = vd.getVeicoli();
                request.setAttribute("lv",lv);
                request.getRequestDispatcher("formPrenotaVeicolo.jsp").forward(request,response);
                break;
            case "visualizzaAllPrenotazioni":
                List<Prenotazione> prenotazioni = new ArrayList<>();
                prenotazioni = pd.getPrenotazioni();
                request.setAttribute("lp",prenotazioni);
                request.getRequestDispatcher("visualizzaAllPrenotazioni.jsp").forward(request,response);
                break;
            case "visualizzaPrenotazioni":
                idUtente = Long.parseLong(request.getParameter("id"));
                u = ud.getUtente(new Utente(idUtente));
                Set<Prenotazione> lp = u.getPrenotazioni();
                request.setAttribute("lp",lp);
                request.getRequestDispatcher("visualizzaPrenotazioni.jsp").forward(request,response);
                break;
            default:
                // code block
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrenotazioneDAO pd = new PrenotazioneDAO();
        VeicoloDAO vd = new VeicoloDAO();
        UtenteDAO ud = new UtenteDAO();
        String comando = request.getParameter("comando");
        Prenotazione p = new Prenotazione();
        Veicolo v = new Veicolo();
        Utente u = new Utente();

        Long idPrenotazione = 0L;
        Long idUtente = 0L;
        Long idVeicolo = 0L;

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

        switch(comando) {
            case "approva":
                boolean approvated = Boolean.parseBoolean(request.getParameter("approva"));
                long id = Long.parseLong(request.getParameter("idPrenotazione"));
                p = pd.getPrenotazione(new Prenotazione(id));
                if(approvated) {
                    p.setApprovato(approvated);
                    pd.saveOrUpdatePrenotazione(p);
                    request.getRequestDispatcher("successo.jsp").forward(request, response);
                }
                else {
                    pd.deletePrenotazione(p);
                    request.getRequestDispatcher("successo.jsp").forward(request, response);
                }
                break;
            case "modifica":
                idPrenotazione = Long.parseLong(request.getParameter("idPrenotazione"));
                idVeicolo = Long.parseLong(request.getParameter("veicolo"));
                v = vd.getVeicolo(new Veicolo(idVeicolo));
                p = pd.getPrenotazione(new Prenotazione(idPrenotazione));
                p.setDataInizio(dataInizio);
                p.setDataFine(dataFine);
                pd.saveOrUpdatePrenotazione(p);
                break;
            case "aggiungi":
                idUtente = Long.parseLong(request.getParameter("idUtente"));
                idVeicolo = Long.parseLong(request.getParameter("veicolo"));
                v = vd.getVeicolo(new Veicolo(idVeicolo));
                u = ud.getUtente(new Utente(idUtente));
                p = new Prenotazione(u,v,dataInizio,dataFine,false);
                pd.saveOrUpdatePrenotazione(p);
                break;
            default:
                // code block
        }
    }
}
