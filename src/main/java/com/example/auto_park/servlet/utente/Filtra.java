package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "Filtra", value = "/Filtra")
public class Filtra extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parametro = request.getParameter("parametro");
        String text = request.getParameter("text");
        System.out.println("Parametro: " + parametro + "," + "Text: " + text);
        UtenteDAO ud = new UtenteDAO();
        List<Utente> clienti = ud.getCustomers();
        List<Utente> filtered = new ArrayList<>();
        switch(parametro){
            case "Nome":
                for (Utente u:clienti){
                    if(u.getNome().contains(text)){
                        filtered.add(u);
                    }
                }
                break;
            case "Cognome":
                for (Utente u:clienti){
                    if(u.getCognome().contains(text)){
                        filtered.add(u);
                    }

                }
                break;
        }
        request.setAttribute("clientiFiltrati", filtered);
        request.getRequestDispatcher("filtrati.jsp").forward(request,response);
    }
}
