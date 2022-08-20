package com.example.auto_park.hibernate.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class MetodiUtili {

    //Inserire metodi static
    public static void errore (String errore,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errore",errore);
        request.getRequestDispatcher("errore.jsp").forward(request, response);
    }
}
