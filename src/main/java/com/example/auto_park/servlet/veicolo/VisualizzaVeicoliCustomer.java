package com.example.auto_park.servlet.veicolo;

import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Veicolo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisualizzaVeicoliCustomer", value = "/VisualizzaVeicoliCustomer")
public class VisualizzaVeicoliCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VeicoloDAO pd = new VeicoloDAO();
        List<Veicolo> lv = pd.getVeicoli();
        System.out.println("Visualizza veicoli: "+lv);
        request.setAttribute("lv",lv);
        request.getRequestDispatcher("visualizzaVeicoliCustomer.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
