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
import java.time.LocalDate;


import java.util.List;
import java.util.Set;


import static java.time.temporal.ChronoUnit.DAYS;

@WebServlet(name = "PrenotazioneServlet", value = "/PrenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {

    VeicoloDAO vd = new VeicoloDAO();
    PrenotazioneDAO pd = new PrenotazioneDAO();
    UtenteDAO ud = new UtenteDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comando = request.getParameter("comando");
        switch (comando) {
            case "richiediPrenotazioneByDates":
                richiediPrenotazioneByDates(request, response);
                break;

            case "visualizzaAllPrenotazioni":
                visualizzaAllPrenotazioni(request, response);
                break;
            default:
                System.out.println("Operazione non selezionata");

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            case "visualizzaVeicoliDisponibili":
                visualizzaVeicoliDisponibili(request, response);
                break;
            case "eliminaPrenotazione":
                eliminaPrenotazione(request, response);
                break;
            default:
                // code block
        }
    }

    //I metodi POST da qua in poi
    private void approvaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean approvated = Boolean.parseBoolean(request.getParameter("approva"));
        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione p = pd.getPrenotazione(Long.parseLong(request.getParameter("idPrenotazione")));
        if (approvated) {
            p.setApprovato(true);
            pd.saveOrUpdatePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        } else {
            pd.deletePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        }
    }

    private void eliminaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Prenotazione p = pd.getPrenotazione(Long.parseLong(request.getParameter("id")));
        LocalDate oggi = LocalDate.now();
        LocalDate dataPrenotazione = p.getDataInizio();
        if ( DAYS.between(oggi, dataPrenotazione) >= 2){
            pd.deletePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        } else {
            System.out.println("Mancano meno di due giorni alla data della prenotazione");
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        }
    }

    private void modificaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        Veicolo veicolo = vd.getVeicolo(Long.parseLong(request.getParameter("veicolo")));
        LocalDate dataInizio = LocalDate.parse(dataInizioString);
        LocalDate dataFine = LocalDate.parse(dataFineString);
        if (dataFine.isAfter(dataInizio)) {
            PrenotazioneDAO pd = new PrenotazioneDAO();
            Prenotazione p = pd.getPrenotazione(Long.parseLong(request.getParameter("idPrenotazione")));
            p.setDataInizio(dataInizio);
            p.setDataFine(dataFine);
            p.setVeicolo(veicolo);
            pd.saveOrUpdatePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        } else {
            System.out.println("La data di fine è precedente alla data di inizio");
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        }

    }

    private void aggiungiPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long idUtente = Long.parseLong(request.getParameter("idUtente"));
        long idVeicolo = Long.parseLong(request.getParameter("idVeicolo"));
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        LocalDate dataInizio = LocalDate.parse(dataInizioString);
        LocalDate dataFine = LocalDate.parse(dataFineString);
        if (dataFine.isAfter(dataInizio)) {
            Veicolo v = vd.getVeicolo(idVeicolo);
            Utente u = ud.getUtente(idUtente);
            Prenotazione p = new Prenotazione(u, v, dataInizio, dataFine, false);
            pd.saveOrUpdatePrenotazione(p);
        }
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    private void visualizzaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Long idUtente = Long.parseLong(request.getParameter("id"));
        UtenteDAO ud = new UtenteDAO();
        Utente u = ud.getUtente(idUtente);
        Set<Prenotazione> lp = u.getPrenotazioni();
        request.setAttribute("listaprenotazioni", lp);
        request.getRequestDispatcher("Prenotazione/visualizzaPrenotazioni.jsp").forward(request, response);
    }

    private void richiediPrenotazioneVeicolo(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        request.setAttribute("utente", u);
        VeicoloDAO vd = new VeicoloDAO();
        List<Veicolo> lv = vd.getVeicoli();
        request.setAttribute("lv", lv);
        request.getRequestDispatcher("Prenotazione/formPrenotaVeicolo.jsp").forward(request, response);
    }

    private void richiediModificaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione p = pd.getPrenotazione(Long.parseLong(request.getParameter("id")));
        LocalDate oggi = LocalDate.now();
        LocalDate dataPrenotazione = p.getDataInizio();
        if ( DAYS.between(oggi, dataPrenotazione) >= 2) {
            VeicoloDAO vd = new VeicoloDAO();
            List<Veicolo> veicoli = vd.getVeicoli();
            request.setAttribute("prenotazione", p);
            request.setAttribute("veicoli", veicoli);
            request.getRequestDispatcher("Prenotazione/formModificaPrenotazione.jsp").forward(request, response);
        } else {
            System.out.println("Mancano meno di due giorni alla data della prenotazione");
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        }
    }

    private void visualizzaAllPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        PrenotazioneDAO pd = new PrenotazioneDAO();
        List<Prenotazione> prenotazioni = pd.getPrenotazioni();
        request.setAttribute("listaprenotazioni", prenotazioni);
        request.getRequestDispatcher("Prenotazione/visualizzaAllPrenotazioni.jsp").forward(request, response);
    }

    private void richiediPrenotazioneByDates(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher("Prenotazione/formPrenotaByDates.jsp").forward(request, response);
    }

    private void visualizzaVeicoliDisponibili(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        LocalDate dataInizio = LocalDate.parse(dataInizioString);
        LocalDate dataFine = LocalDate.parse(dataFineString);
        PrenotazioneDAO pd = new PrenotazioneDAO();
        List<Prenotazione> prenotazioniByDates = pd.getPrenotazioniByDates(dataInizio, dataFine);
        VeicoloDAO vd = new VeicoloDAO();
        //prelevo la lista di tutti i veicoli e poi rimuovo quelli prenotati
        List<Veicolo> veicoliDisponibili = vd.getVeicoli();
        for (Veicolo v : veicoliDisponibili) {
            for (Prenotazione pbd : prenotazioniByDates) {
                if (pbd.isApprovato() && !v.getId().equals(pbd.getVeicolo().getId())) {
                    veicoliDisponibili.remove(v);
                }
            }
            break;
        }
        request.setAttribute("dataInizio", dataInizioString);
        request.setAttribute("dataFine", dataFineString);
        request.setAttribute("veicoliDisponibili", veicoliDisponibili);
        request.getRequestDispatcher("Prenotazione/prenotaByDates.jsp").forward(request, response);
    }

}
