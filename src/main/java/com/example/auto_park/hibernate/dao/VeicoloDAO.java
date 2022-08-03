package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Veicolo;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Veicolo> cr = cb.createQuery(Veicolo.class);
        Root<Veicolo> root = cr.from(Veicolo.class);
        cr.select(root);
        Query<Veicolo> query = session.createQuery(cr);
        List<Veicolo> results = query.getResultList();
        return  results;
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
