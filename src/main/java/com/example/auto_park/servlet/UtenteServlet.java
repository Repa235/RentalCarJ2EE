package com.example.auto_park.servlet;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;


import java.util.List;

import static com.example.auto_park.hibernate.util.MetodiUtili.errore;

@WebServlet(name = "UtenteServlet", value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {


    UtenteDAO ud = new UtenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String comando = request.getParameter("comando");
        switch (comando) {
            case "profiloS":
                profiloSuper(request, response);
                break;
            case "profiloC":
                profiloCustomer(request, response);
                break;
            case "profiloUtente":
                profiloUtente(request, response);
                break;
            case "richiediAggiungiUtente":
                richiediAggiungiUtente(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                errore("Si è verificato un errore", request,response);;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comando = request.getParameter("comando");
        switch (comando) {
            case "aggiungi":
                aggiungiUtente(request, response);
                break;
            case "elimina":
                eliminaUtente(request, response);
                break;
            case "modifica":
                modificaUtente(request, response);
                break;
            case "login":
                login(request, response);
                break;
            case "filtraUtenti":
                filtraUtente(request, response);
                break;
            case "richiediModificaUtente":
                richiedimodificaUtente(request, response);
                break;
            case "richiediModificaUtenteBySuperUser":
                richiedimodificaUtenteBySuperUser(request, response);
                break;
            default:
                errore("Si è verificato un errore", request,response);
        }

    }


    public void profiloSuper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long idSuper = ((Utente) session.getAttribute("utente")).getId();
        List<Utente> clienti = ud.getCustomers();
        Utente u = ud.getUtente(idSuper);
        session.setAttribute("superUser", u);
        request.setAttribute("clienti", clienti);
        request.getRequestDispatcher("Utente/profiloSuper.jsp").forward(request, response);
    }

    public void richiediAggiungiUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Utente/formAggiungiUtente.jsp").forward(request, response);
    }

    public void profiloCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long idCustomer = ((Utente) session.getAttribute("utente")).getId();
        Utente utente = ud.getUtente(idCustomer);
        LocalDate now = LocalDate.now();
        session.setAttribute("utente", utente);
        request.setAttribute("now", now);
        request.getRequestDispatcher("Utente/profiloCustomer.jsp").forward(request, response);
    }

    public void profiloUtente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String type = ((Utente) session.getAttribute("utente")).getTipo();
        if (type.equals("customer")) {
            response.sendRedirect("UtenteServlet?comando=profiloC");
        }
        if (type.equals("superuser")) {
            response.sendRedirect("UtenteServlet?comando=profiloS");
        }

    }


    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("utente");
        session.invalidate();
        response.sendRedirect("Homepage");
    }


    public void aggiungiUtente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        LocalDate dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
        String tipo = "customer";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Utente u = new Utente(nome, cognome, dataNascita, tipo, username, password, null);
        ud.saveOrUpdateUtente(u);
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    public void eliminaUtente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Utente uE = ud.getUtente(id);
        try {
            ud.deleteUtente(uE);
        } catch (Exception e) {
            e.printStackTrace();
            errore("Eliminazione fallita, l'utente selezionato potrebbe avere delle prenotazioni in sospeso", request, response);
        }
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    public void modificaUtente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        LocalDate dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
        String tipo = "customer";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Utente uM = new Utente(id, nome, cognome, dataNascita, tipo, username, password, null);
        ud.saveOrUpdateUtente(uM);
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    public void richiedimodificaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idCustomer = Long.parseLong(request.getParameter("id"));
        Utente uM = ud.getUtente(idCustomer);
        request.setAttribute("utenteDaModificare", uM);
        request.getRequestDispatcher("Utente/formModificaUtente.jsp").forward(request, response);
    }

    public void richiedimodificaUtenteBySuperUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idCustomer = Long.parseLong(request.getParameter("id"));
        Utente uM = ud.getUtente(idCustomer);
        request.setAttribute("utenteDaModificare", uM);
        request.getRequestDispatcher("Utente/formModificaUtente.jsp").forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Utente u = new Utente();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Utente> sameUsername = ud.getUsersByUsername(username);
        for (Utente u1 : sameUsername) {
            if (u1.getPassword().equals(password)) {
                u = u1;
            }
        }
        session.setAttribute("utente", u);
        if (u.getTipo().equals("customer")) {
            response.sendRedirect("UtenteServlet?comando=profiloC");
        } else {
            response.sendRedirect("UtenteServlet?comando=profiloS");
        }
    }

    public void filtraUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtraPer = request.getParameter("filtraPer");
        String text = request.getParameter("text");
        List<Utente> filtered;
        switch (filtraPer) {
            case "Nome":
                filtered = ud.getUtentiFiltratiByNome(text);
                request.setAttribute("clienti", filtered);
                request.getRequestDispatcher("Utente/filtrati.jsp").forward(request, response);
                break;
            case "Cognome":
                filtered = ud.getUtentiFiltratiByCognome(text);
                request.setAttribute("clienti", filtered);
                request.getRequestDispatcher("Utente/filtrati.jsp").forward(request, response);
                break;
            default:
                errore("Nessun parametro selezionato", request, response);
        }
    }

}
