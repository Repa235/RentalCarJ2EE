package com.example.auto_park.servlet;

import com.example.auto_park.MetodiUtili;
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


    MetodiUtili mU = new MetodiUtili();

    PrenotazioneDAO pd = new PrenotazioneDAO();
    VeicoloDAO vd = new VeicoloDAO();
    UtenteDAO ud = new UtenteDAO();


    Prenotazione p = new Prenotazione();
    Veicolo v = new Veicolo();
    Utente u = new Utente();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String comando = request.getParameter("comando");

        switch (comando) {

            case "visualizzaAllPrenotazioni":
                visualizzaAllPrenotazioni(request, response);
                break;
            default:
                // code block
        }
    }

    //I metodi GET da qua in poi


    private void visualizzaAllPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        prenotazioni = pd.getPrenotazioni();
        request.setAttribute("listaprenotazioni", prenotazioni);
        request.getRequestDispatcher("Prenotazione/visualizzaAllPrenotazioni.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String comando = request.getParameter("comando");


        switch (comando) {
            case "approvaPrenotazione":
                approvaPrenotazione(request, response);
                break;
            case "modificaPrenotazione":
                modificaPrenotazione(request, response);
                break;
            case "aggiungiPrenotazione":
                aggiungiPrenotazione(request, response);
                break;
            case "visualizzaPrenotazioni":
                visualizzaPrenotazioni(request, response);
                break;
            case "richiediModificaPrenotazione":
                richiediModificaPrenotazione(request, response);
                break;
            case "richiediPrenotazioneVeicolo":
                richiediPrenotazioneVeicolo(request, response);
                break;
            default:
                // code block
        }
    }

    //I metodi POST da qua in poi
    private void approvaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean approvated = Boolean.parseBoolean(request.getParameter("approva"));
        p = pd.getPrenotazione(new Prenotazione(Long.parseLong(request.getParameter("idPrenotazione"))));
        if (approvated) {
            p.setApprovato(true);
            pd.saveOrUpdatePrenotazione(p);
            request.getRequestDispatcher("successo.jsp").forward(request, response);
        } else {
            pd.deletePrenotazione(p);
            request.getRequestDispatcher("successo.jsp").forward(request, response);
        }


    }

    private void modificaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idPrenotazione = Long.parseLong(request.getParameter("idPrenotazione"));
        long idVeicolo = Long.parseLong(request.getParameter("veicolo"));
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        Date dataInizio = mU.stringToDate(dataInizioString);
        Date dataFine = mU.stringToDate(dataFineString);
        v = vd.getVeicolo(new Veicolo(idVeicolo));
        p = pd.getPrenotazione(new Prenotazione(idPrenotazione));
        p.setDataInizio(dataInizio);
        p.setDataFine(dataFine);
        pd.saveOrUpdatePrenotazione(p);
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    private void aggiungiPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idUtente = Long.parseLong(request.getParameter("idUtente"));
        long idVeicolo = Long.parseLong(request.getParameter("veicolo"));
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        Date dataInizio = mU.stringToDate(dataInizioString);
        Date dataFine = mU.stringToDate(dataFineString);
        v = vd.getVeicolo(new Veicolo(idVeicolo));
        u = ud.getUtente(new Utente(idUtente));
        p = new Prenotazione(u, v, dataInizio, dataFine, false);
        pd.saveOrUpdatePrenotazione(p);
    }

    private void visualizzaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idUtente = Long.parseLong(request.getParameter("id"));
        u = ud.getUtente(new Utente(idUtente));
        Set<Prenotazione> lp = u.getPrenotazioni();
        request.setAttribute("listaprenotazioni", lp);
        request.getRequestDispatcher("Prenotazione/visualizzaPrenotazioni.jsp").forward(request, response);
    }

    private void richiediPrenotazioneVeicolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        u = (Utente) session.getAttribute("utente");
        request.setAttribute("utente", u);
        List<Veicolo> lv = new ArrayList<>();
        lv = vd.getVeicoli();
        request.setAttribute("lv", lv);
        request.getRequestDispatcher("Prenotazione/formPrenotaVeicolo.jsp").forward(request, response);
    }

    private void richiediModificaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idPrenotazione = Long.parseLong(request.getParameter("id"));
        p = pd.getPrenotazione(new Prenotazione(idPrenotazione));
        List<Veicolo> veicoli = vd.getVeicoli();
        request.setAttribute("prenotazione", p);
        request.setAttribute("veicoli", veicoli);
        request.getRequestDispatcher("Prenotazione/formModificaPrenotazione.jsp").forward(request, response);
    }
}
