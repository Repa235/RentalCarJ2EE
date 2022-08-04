package com.example.auto_park.hibernate.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MetodiUtili {

    public static Date stringToDate(String s){
        Date res = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            res = sdf.parse(s);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }
        return res;
    }

    public static int getGiorniDiDifferenza(Date precedente, Date successivo) {
        long diff = Math.abs(successivo.getTime() - precedente.getTime());
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static LocalDateTime dateToLocalDate (Date d){
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
