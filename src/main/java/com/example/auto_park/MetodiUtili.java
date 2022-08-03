package com.example.auto_park;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MetodiUtili {

    public Date stringToDate(String s){
        Date res = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            res = sdf.parse(s);
        } catch (Exception e) {
            System.out.println("Impossibile prelevare la data");
        }
        return res;
    }
}
