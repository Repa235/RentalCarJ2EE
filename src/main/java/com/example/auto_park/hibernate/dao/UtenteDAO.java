package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UtenteDAO {
    private HibernateAnnotationUtil HibernateUtil;

    public Utente getUtente(Utente c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            c = (Utente) session.get(Utente.class, c.getId());
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return c;
    }

    public boolean saveOrUpdateUtente(Utente c) {
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

}
