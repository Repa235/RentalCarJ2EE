package com.example.auto_park;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;

import java.util.ArrayList;
import java.util.List;

public class Prova {
    public static void main(String[] args) {
        UtenteDAO vd = new UtenteDAO();
        PrenotazioneDAO pd = new PrenotazioneDAO();
        List<Prenotazione> listP = new ArrayList<>();

        Utente u = vd.getUtente(new Utente(Long.valueOf(1)));
        //listP=pd.getPrenotazioniByUtente(u);
        System.out.println("Prenotazioni: "+listP);
        for (Prenotazione v : listP){
            System.out.println(v.getId());
        }

    }
}
