package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Veicolo;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VeicoloDAO {
    private HibernateAnnotationUtil HibernateUtil;

    public Veicolo getVeicolo(Veicolo c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            c = (Veicolo) session.get(Veicolo.class, c.getId());
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return c;
    }
    public List<Veicolo> getVeicoli() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Veicolo> lp;
        try {
            lp = (List<Veicolo>) session.createQuery("from Veicolo").list();
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return lp;
    }

    public boolean saveOrUpdateVeicolo(Veicolo c) {
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

    public boolean deleteVeicolo(Veicolo c) {
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
