package com.example.auto_park.servlet.utente;

import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UtenteDAO ud = new UtenteDAO();
        List<Utente> sameUsername = new ArrayList<Utente>();
        Utente u = new Utente();
        u.setUsername(username);
        sameUsername=ud.getUsersByUsername(u);
        for (Utente u1 : sameUsername){
            if (u1.getPassword().equals(password)){
                System.out.println("U: " + u1.getUsername() + " " + "P: " + u1.getPassword() );
                u = u1;
            }
        }

        session.setAttribute("utente",u);

        if(u.getTipo().equals("customer")){

            request.getRequestDispatcher("profiloCustomer.jsp").forward(request,response);
        }else {
            response.sendRedirect("ProfiloUtenteSuper");
            //request.getRequestDispatcher("profiloSuper.jsp").forward(request,response);
        }
    }
}
