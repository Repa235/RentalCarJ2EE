package com.example.auto_park;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Veicolo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prova {

    public static void main(String[] args) {
        VeicoloDAO vd = new VeicoloDAO();
        PrenotazioneDAO pd = new PrenotazioneDAO();
        UtenteDAO ud = new UtenteDAO();

        Date dI = new Date();
        dI.setDate(02);
        dI.setMonth(7);
        dI.setYear(2022);
        Date dF = new Date();
        dI.setDate(02);
        dI.setMonth(10);
        dI.setYear(2022);

        List<Prenotazione> lp = pd.getFilteredPrenotazioniByData(dI,dF);
        System.out.println(dI);
        System.out.println(dF);

        for (Prenotazione p : lp ) {
            System.out.println(p.getId());
        }

    }
}
