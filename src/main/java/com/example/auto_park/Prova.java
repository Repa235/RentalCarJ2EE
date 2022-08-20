package com.example.auto_park;

import com.example.auto_park.hibernate.dao.PrenotazioneDAO;
import com.example.auto_park.hibernate.dao.UtenteDAO;
import com.example.auto_park.hibernate.dao.VeicoloDAO;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Veicolo;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import com.example.auto_park.hibernate.util.MetodiUtili;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.predicate.BooleanAssertionPredicate;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Prova {


    public static void main(String[] args) {

        HibernateAnnotationUtil HibernateUtil = null;

        LocalDate dataSceltaI = LocalDate.of(2022, Month.AUGUST,01);
        LocalDate dataSceltaF = LocalDate.of(2022, Month.AUGUST,30);






        }
}
