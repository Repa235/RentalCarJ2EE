package com.example.auto_park.servlet.veicolo;

import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AggiungiVeicolo", value = "/AggiungiVeicolo")
public class AggiungiVeicolo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int annoImmatricolazione = Integer.parseInt(request.getParameter("annoImmatricolazione"));
        String casaCostruttrice = request.getParameter("casaCostruttrice");
        String modello = request.getParameter("modello");
        String tipo = request.getParameter("tipo");

        Veicolo v = new Veicolo(casaCostruttrice,modello,annoImmatricolazione,tipo);
        VeicoloDAO vd = new VeicoloDAO();
        vd.saveOrUpdateVeicolo(v);

        System.out.println("Veicolo aggiunto");
        request.getRequestDispatcher("successo.jsp").forward(request,response);
    }
}
