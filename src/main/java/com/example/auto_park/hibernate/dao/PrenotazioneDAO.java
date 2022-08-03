package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
//import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrenotazioneDAO {
    private HibernateAnnotationUtil HibernateUtil;

    public Prenotazione getPrenotazione(Prenotazione c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            c = (Prenotazione) session.get(Prenotazione.class, c.getId());
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return c;
    }

    public List<Prenotazione> getPrenotazioni() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Prenotazione> lp;
        try {
            lp = (List<Prenotazione>) session.createQuery("from Prenotazione").list();
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return lp;
    }

   /* public List<Prenotazione> getPrenotazioniByUtente(Prenotazione utente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Prenotazione> lp;
        long id = utente.getId();
        try {
            lp = (List<Prenotazione>) session.createQuery("from Prenotazione p where p.utente=:utente").setParameter("utente",utente).list();
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return lp;
    }*/

    public boolean saveOrUpdatePrenotazione(Prenotazione c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(c); ;
            transaction.commit();
            result = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return result;
    }

    public boolean deletePrenotazione(Prenotazione c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.delete(c);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return result;
    }

    public List<Prenotazione> getFilteredPrenotazioniByData(Date dataSceltaI, Date dataSceltaF) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> cr = cb.createQuery(Prenotazione.class);
        Root<Prenotazione> root = cr.from(Prenotazione.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.greaterThan(root.<Date>get("dataInizio"), dataSceltaI);
        predicates[1] = cb.lessThanOrEqualTo(root.<Date>get("dataInizio"), dataSceltaF);
        cr.select(root).where(predicates);

        Query<Prenotazione> query = session.createQuery(cr);
        List<Prenotazione> results = query.getResultList();
        return  results;
    }
}
