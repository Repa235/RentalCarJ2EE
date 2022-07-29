package com.example.auto_park.servlet.veicolo;

import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisualizzaVeicoli", value = "/VisualizzaVeicoli")
public class VisualizzaVeicoli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VeicoloDAO pd = new VeicoloDAO();
        List<Veicolo> lv = pd.getVeicoli();
        System.out.println("Visualizza veicoli: "+lv);
        request.setAttribute("lv",lv);
        request.getRequestDispatcher("visualizzaVeicoli.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
