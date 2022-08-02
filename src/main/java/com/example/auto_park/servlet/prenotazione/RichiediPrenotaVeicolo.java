package com.example.auto_park.servlet.prenotazione;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RichiediPrenotaVeicolo", value = "/RichiediPrenotaVeicolo")
public class RichiediPrenotaVeicolo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        UtenteDAO ud = new UtenteDAO();
        Utente u = (Utente) s.getAttribute("utente");
        request.setAttribute("utente",u);
        VeicoloDAO pd = new VeicoloDAO();
        List<Veicolo> lv = new ArrayList<>();
               lv = pd.getVeicoli();
        request.setAttribute("lv",lv);
        request.getRequestDispatcher("formPrenotaVeicolo.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}