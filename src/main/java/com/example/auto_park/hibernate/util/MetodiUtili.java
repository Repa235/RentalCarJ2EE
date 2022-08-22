package com.example.auto_park.hibernate.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MetodiUtili {
    public static void errore (String errore,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errore",errore);
        request.getRequestDispatcher("errore.jsp").forward(request, response);
    }
}
