package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class PrenotazioneDAO {
    private HibernateAnnotationUtil HibernateUtil;
    public Prenotazione getPrenotazione(Long id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.get(Prenotazione.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    public List<Prenotazione> getPrenotazioni() {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            return  (List<Prenotazione>) session.createQuery("from Prenotazione").list();
        } catch (HibernateException e) {
            return null;
        }
    }

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

    public List<Prenotazione> getPrenotazioniByDates(LocalDate dataSceltaI, LocalDate dataSceltaF) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> cr = cb.createQuery(Prenotazione.class);
        Root<Prenotazione> root = cr.from(Prenotazione.class);
        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.greaterThan(root.<LocalDate>get("dataInizio"), dataSceltaI);
        predicates[1] = cb.lessThanOrEqualTo(root.<LocalDate>get("dataFine"), dataSceltaF);
        cr.select(root).where(predicates);
        Query<Prenotazione> query = session.createQuery(cr);
        List<Prenotazione> results = query.getResultList();
        return  results;
    }
}
