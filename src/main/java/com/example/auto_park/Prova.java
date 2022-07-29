package com.example.auto_park;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;

import java.util.ArrayList;
import java.util.List;

public class Prova {
    public static void main(String[] args) {
        PrenotazioneDAO vd = new PrenotazioneDAO();
        List<Prenotazione> listP = new ArrayList<>();
        listP=vd.getPrenotazioni();
        for (Prenotazione v : listP){
            System.out.println(v.getUtente().getNome());
        }

    }
}
