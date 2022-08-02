package com.example.auto_park.servlet.prenotazione;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RichiediModificaPrenotazione", value = "/RichiediModificaPrenotazione")
public class RichiediModificaPrenotazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        PrenotazioneDAO pd = new PrenotazioneDAO();
        VeicoloDAO vd = new VeicoloDAO();
        Prenotazione p = pd.getPrenotazione(new Prenotazione(id));
        List<Veicolo> veicoli = vd.getVeicoli();
        request.setAttribute("prenotazione", p);
        request.setAttribute("veicoli", veicoli);
        request.getRequestDispatcher("formModificaPrenotazione.jsp").forward(request,response);
    }
}
