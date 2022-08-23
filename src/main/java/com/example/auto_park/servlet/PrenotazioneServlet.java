package com.example.auto_park.servlet;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.example.auto_park.hibernate.util.MetodiUtili.errore;
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
                errore("Operazione non selezionata", request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comando = request.getParameter("comando");

        switch (comando) {
            case "approvaPrenotazione":
                approvaPrenotazione(request, response);
                break;
            case "modificaAggiungiPrenotazione":
                modificaAggiungiPrenotazione(request, response);
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
                errore("Operazione non selezionata", request,response);
        }
    }

    private void approvaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String approvated = request.getParameter("approva");
        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione p = pd.getPrenotazione(Long.parseLong(request.getParameter("idPrenotazione")));
        if (approvated.equals("true")) {
            p.setApprovato(true);
            pd.saveOrUpdatePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        } else if(approvated.equals("false")) {
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        } else {
            pd.deletePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        }
    }

    private void eliminaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Prenotazione p = pd.getPrenotazione(Long.parseLong(request.getParameter("id")));
        LocalDate oggi = LocalDate.now();
        LocalDate dataPrenotazione = p.getDataInizio();
        if ( DAYS.between(oggi, dataPrenotazione) >= 2){
            pd.deletePrenotazione(p);
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        } else {
            System.out.println("Mancano meno di due giorni alla data della prenotazione");
            errore("Mancano meno di due giorni alla data della prenotazione",request,response);
        }
    }

    private void modificaAggiungiPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_string = request.getParameter("idPrenotazione");
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        LocalDate dataInizio = LocalDate.parse(dataInizioString);
        LocalDate dataFine = LocalDate.parse(dataFineString);
        if (id_string!=null){
            Long id = Long.parseLong(id_string);
            Prenotazione p = pd.getPrenotazione(id);
            Veicolo veicolo = vd.getVeicolo(Long.parseLong(request.getParameter("veicolo")));
            List<Veicolo> liberiNelRange = vd.getVeicoliLiberiNelRange(dataInizio,dataFine);
            if(liberiNelRange.contains(veicolo)) {
                if (dataFine.isAfter(dataInizio)) {
                    PrenotazioneDAO pd = new PrenotazioneDAO();
                    p.setDataInizio(dataInizio);
                    p.setDataFine(dataFine);
                    p.setVeicolo(veicolo);
                    p.setApprovato(false);
                    pd.saveOrUpdatePrenotazione(p);
                    response.sendRedirect("UtenteServlet?comando=profiloUtente");
                } else {
                    System.out.println("La data di fine è precedente alla data di inizio");
                    response.sendRedirect("UtenteServlet?comando=profiloUtente");
                }
            } else {
                errore("Il veicolo indicato risulta occupato nell'intervallo scelto",request,response);
            }
        } else {
            long idUtente = Long.parseLong(request.getParameter("idUtente"));
            long idVeicolo = Long.parseLong(request.getParameter("idVeicolo"));
            if (dataFine.isAfter(dataInizio)) {
                Veicolo v = vd.getVeicolo(idVeicolo);
                Utente u = ud.getUtente(idUtente);
                Prenotazione p = new Prenotazione(u, v, dataInizio, dataFine, false);
                pd.saveOrUpdatePrenotazione(p);
            }
            response.sendRedirect("UtenteServlet?comando=profiloUtente");
        }
    }

    private void visualizzaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Long idUtente = Long.parseLong(request.getParameter("id"));
        UtenteDAO ud = new UtenteDAO();
        Utente u = ud.getUtente(idUtente);
        Set<Prenotazione> lp = u.getPrenotazioni();
        request.setAttribute("listaprenotazioni", lp);
        request.getRequestDispatcher("Prenotazione/visualizzaAllPrenotazioni.jsp").forward(request, response);
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
        }
        else if (DAYS.between(oggi, dataPrenotazione)<=0) {
            errore("Il periodo di prenotazione del veicolo è già trascorso",request,response);
        }
        else {
            errore("Mancano meno di due giorni alla data della prenotazione",request,response);
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
        request.getRequestDispatcher("Prenotazione/formSelezionaDatePrenotazione.jsp").forward(request, response);
    }

    private void visualizzaVeicoliDisponibili(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String dataInizioString = request.getParameter("dataInizio");
        String dataFineString = request.getParameter("dataFine");
        LocalDate dataInizio = LocalDate.parse(dataInizioString);
        LocalDate dataFine = LocalDate.parse(dataFineString);
        PrenotazioneDAO pd = new PrenotazioneDAO();
        VeicoloDAO vd = new VeicoloDAO();
        List<Veicolo> veicoliDisponibili = vd.getVeicoliLiberiNelRange(dataInizio,dataFine);
        request.setAttribute("dataInizio", dataInizioString);
        request.setAttribute("dataFine", dataFineString);
        request.setAttribute("veicoliDisponibili", veicoliDisponibili);
        request.getRequestDispatcher("Prenotazione/formSelezionaVeicolo.jsp").forward(request, response);
    }

}
