package com.example.auto_park.servlet;

import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VeicoloServlet", value = "/VeicoloServlet")
public class VeicoloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String comando = request.getParameter("comando");
        VeicoloDAO vd = new VeicoloDAO();
        switch(comando) {
            case "visualizzaVeicoli":
                List<Veicolo> lv = vd.getVeicoli();
                request.setAttribute("lv",lv);
                request.getRequestDispatcher("visualizzaVeicoli.jsp").forward(request,response);
                break;
            case "":
                // code block
                break;
            default:
                // code block
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        VeicoloDAO vd = new VeicoloDAO();
        String comando = request.getParameter("comando");
        int annoImmatricolazione = Integer.parseInt(request.getParameter("annoImmatricolazione"));
        String casaCostruttrice = request.getParameter("casaCostruttrice");
        String modello = request.getParameter("modello");
        String tipo = request.getParameter("tipo");

        switch(comando) {
            case "aggiungi":
                Veicolo v = new Veicolo(casaCostruttrice,modello,annoImmatricolazione,tipo);
                vd.saveOrUpdateVeicolo(v);
                request.getRequestDispatcher("successo.jsp").forward(request,response);
                break;
            case "":
                // code block
                break;
            default:
                // code block
        }
    }
}
