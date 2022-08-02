package com.example.auto_park.servlet;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UtenteServlet", value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String comando = request.getParameter("comando");
        switch (comando) {
            case "profiloS":
                Utente superUser = (Utente) session.getAttribute("utente");
                System.out.println("Utente: " + superUser.getNome() + " " + superUser.getCognome());
                UtenteDAO ud = new UtenteDAO();
                List<Utente> clienti = ud.getCustomers();
                request.setAttribute("superUser", superUser);
                request.setAttribute("clienti", clienti);
                System.out.println("Clienti:" + clienti);
                request.getRequestDispatcher("profiloSuper.jsp").forward(request, response);
                break;
            case "profiloC":
                Utente customer = (Utente) session.getAttribute("utente");
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("profiloCustomer.jsp").forward(request, response);
                break;
            case "richiediModifica":
                Utente u = (Utente) session.getAttribute("utente");
                request.setAttribute("utente", u);
                request.getRequestDispatcher("formModificaUtente.jsp").forward(request, response);
                break;
            case "profiloUtente":
                Utente uP = (Utente) session.getAttribute("utente");
                if (uP.getTipo() == "customer") {
                    response.sendRedirect("UtenteServlet?comando=profiloC");
                } else {
                    response.sendRedirect("UtenteServlet?comando=profiloS");
                }
                break;
            case "logout":
                session.removeAttribute("utente");
                session.invalidate();
                response.sendRedirect("Homepage");
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String comando = request.getParameter("comando");
        Long id = 0L;
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


        switch (comando) {
            case "aggiungi":
                Utente uA = new Utente(nome, cognome, dataNascita, tipo, username, password, null);
                ud.saveOrUpdateUtente(uA);
                break;
            case "elimina":
                id = Long.parseLong(request.getParameter("id"));
                Utente uE = ud.getUtente(new Utente(id));
                ud.deleteUtente(uE);
                break;
            case "modifica":
                id = Long.parseLong(request.getParameter("id"));
                Utente uM = new Utente(id, nome, cognome, dataNascita, tipo, username, password, null);
                ud.saveOrUpdateUtente(uM);
                break;
            case "login":
                HttpSession session = request.getSession();
                List<Utente> sameUsername = new ArrayList<Utente>();
                Utente uL = new Utente();
                uL.setUsername(username);
                sameUsername = ud.getUsersByUsername(uL);
                for (Utente u1 : sameUsername) {
                    if (u1.getPassword().equals(password)) {
                        uL = u1;
                    }
                }
                session.setAttribute("utente", uL);
                if (uL.getTipo().equals("customer")) {
                    response.sendRedirect("UtenteServlet?comando=profiloC");
                } else {
                    response.sendRedirect("UtenteServlet?comando=profiloS");
                }
                break;
            case "filtra":
                String parametro = request.getParameter("parametro");
                String text = request.getParameter("text");
                List<Utente> clienti = ud.getCustomers();
                List<Utente> filtered = new ArrayList<>();
                switch (parametro) {
                    case "Nome":
                        // code block
                        break;
                    case "Cognome":
                        // code block
                        break;
                    case "Data":
                        // code block
                        break;
                    default:
                        // code block
                }
                break;
            default:
                // code block
        }

    }
}
