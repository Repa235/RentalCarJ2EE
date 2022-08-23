package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

}
