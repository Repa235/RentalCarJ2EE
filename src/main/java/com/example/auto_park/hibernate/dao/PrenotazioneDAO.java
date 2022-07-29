package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            //lp = (List<Prenotazione>) session.createQuery("from Prenotazione").list(); //con HQL non funziona
            lp = (List<Prenotazione>) session.createQuery("from Prenotazione").list();
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return lp;
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
}
