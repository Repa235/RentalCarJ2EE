package com.example.auto_park.servlet;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UtenteServlet", value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {


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
            default:
        }
    }

    public void profiloSuper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        UtenteDAO ud = new UtenteDAO();
        List<Utente> clienti = ud.getCustomers();
        request.setAttribute("superUser", u);
        request.setAttribute("clienti", clienti);
        System.out.println("Clienti:" + clienti);
        request.getRequestDispatcher("Utente/profiloSuper.jsp").forward(request, response);
    }

    public void richiediAggiungiUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Utente/formAggiungiUtente.jsp").forward(request, response);
        System.out.println("AAAAA");
    }

    public void profiloCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        request.setAttribute("utente", u);
        request.getRequestDispatcher("Utente/profiloCustomer.jsp").forward(request, response);
    }

    public void profiloUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        System.out.println(u.getNome() + " " + u.getCognome() + " Tipo: " + u.getTipo());
        if (u.getTipo().equals("customer")) {
            response.sendRedirect("UtenteServlet?comando=profiloC");
        }
        if (u.getTipo().equals("superuser")) {
            response.sendRedirect("UtenteServlet?comando=profiloS");
        }

    }


    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("utente");
        session.invalidate();
        response.sendRedirect("Homepage");
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
                // code block
        }

    }

    public void aggiungiUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascitaString = request.getParameter("dataNascita");
        Date dataNascita = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dataNascita = (Date) sdf.parse(dataNascitaString);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }
        String tipo = "customer";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UtenteDAO ud = new UtenteDAO();
        Utente u = new Utente(nome, cognome, dataNascita, tipo, username, password, null);
        ud.saveOrUpdateUtente(u);
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    public void eliminaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        UtenteDAO ud = new UtenteDAO();
        Utente uE = ud.getUtente(id);
        try {
            ud.deleteUtente(uE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Eliminazione fallita, l'utente potrebbe avere delle prenotazioni in sospeso");
        }
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    public void modificaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO ud = new UtenteDAO();
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascitaString = request.getParameter("dataNascita");
        Date dataNascita = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dataNascita = (Date) sdf.parse(dataNascitaString);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }
        String tipo = "customer";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Utente uM = new Utente(id, nome, cognome, dataNascita, tipo, username, password, null);
        ud.saveOrUpdateUtente(uM);
        response.sendRedirect("UtenteServlet?comando=profiloUtente");
    }

    public void richiedimodificaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idCustomer = Long.parseLong(request.getParameter("id"));
        UtenteDAO ud = new UtenteDAO();
        Utente uM = ud.getUtente(idCustomer);
        request.setAttribute("utenteDaModificare", uM);
        request.getRequestDispatcher("Utente/formModificaUtente.jsp").forward(request, response);
    }

    public void richiedimodificaUtenteBySuperUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idCustomer = Long.parseLong(request.getParameter("id"));
        UtenteDAO ud = new UtenteDAO();
        Utente uM = ud.getUtente(idCustomer);
        request.setAttribute("utenteDaModificare", uM);
        request.getRequestDispatcher("Utente/formModificaUtente.jsp").forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = new Utente();
        UtenteDAO ud = new UtenteDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Utente> sameUsername = new ArrayList<Utente>();
        sameUsername = ud.getUsersByUsername(username);
        for (Utente u1 : sameUsername) {
            if (u1.getPassword().equals(password)) {
                u = u1;
            }
        }
        System.out.println("Utente trovato " + u.getNome());
        session.setAttribute("utente", u);
        if (u.getTipo().equals("customer")) {
            response.sendRedirect("UtenteServlet?comando=profiloC");
        } else {
            response.sendRedirect("UtenteServlet?comando=profiloS");
        }
    }

    public void filtraUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO ud = new UtenteDAO();
        String filtraPer = request.getParameter("filtraPer");
        String text = request.getParameter("text");
        List<Utente> filtered = new ArrayList<>();
        switch (filtraPer) {
            case "Nome":
                filtered = ud.getUtentiFiltratiByNome(text);
                request.setAttribute("clienti", filtered);
                request.getRequestDispatcher("Utente/filtrati.jsp").forward(request, response);
                break;
            case "Cognome":
                filtered = ud.getUtentiFiltratiByCognome(text);
                System.out.println(filtered.toString());
                request.setAttribute("clienti", filtered);
                request.getRequestDispatcher("Utente/filtrati.jsp").forward(request, response);
                break;
            default:
                // code block
        }
    }

}
