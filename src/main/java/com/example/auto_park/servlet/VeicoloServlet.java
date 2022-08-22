package com.example.auto_park.servlet;


import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.auto_park.hibernate.util.MetodiUtili.errore;

@WebServlet(name = "VeicoloServlet", value = "/VeicoloServlet")
public class VeicoloServlet extends HttpServlet {

    VeicoloDAO vd = new VeicoloDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String comando = request.getParameter("comando");

        switch (comando) {
            case "visualizzaVeicoli":
                visualizzaVeicoli(request, response);
                break;
            case "richiediAggiungiVeicolo":
                richiediAggiungiVeicolo(request, response);
                break;
            default:
                errore("Operazione non selezionata", request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String comando = request.getParameter("comando");


        switch (comando) {
            case "aggiungiVeicolo":
                aggiungiVeicolo(request, response);
                break;
            default:
                errore("Operazione non selezionata", request,response);
        }
    }

    private void visualizzaVeicoli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Veicolo> lv = vd.getVeicoli();
        request.setAttribute("listaveicoli", lv);
        request.getRequestDispatcher("Veicolo/visualizzaVeicoliCustomer.jsp").forward(request, response);
    }

    private void richiediAggiungiVeicolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Veicolo/formAggiungiVeicolo.jsp").forward(request, response);
    }


    private void aggiungiVeicolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int annoImmatricolazione = Integer.parseInt(request.getParameter("annoImmatricolazione"));
        String casaCostruttrice = request.getParameter("casaCostruttrice");
        String modello = request.getParameter("modello");
        String tipo = request.getParameter("tipo");
        Veicolo v = new Veicolo(casaCostruttrice, modello, annoImmatricolazione, tipo);
        vd.saveOrUpdateVeicolo(v);
        request.getRequestDispatcher("successo.jsp").forward(request, response);
    }
}
