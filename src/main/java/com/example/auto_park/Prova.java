package com.example.auto_park;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.util.MetodiUtili;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Prova {


    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        System.out.println(today);
    }
}
