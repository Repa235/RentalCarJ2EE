package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
            session.saveOrUpdate(c);
            ;
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

    public boolean deleteUtente(Utente c) {
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

    public List<Utente> getCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Utente> lp;
        try {
            lp = (List<Utente>) session.createQuery("from Utente where tipo = 'customer'").list();
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return lp;
    }

    public List<Utente> getUsersByUsername(Utente utente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Utente> lp;
        try {
            lp = (List<Utente>) session.createQuery("from Utente where username=:un")
                    .setParameter("un",utente.getUsername())
                    .list();
        } catch (HibernateException e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return lp;
    }

    public List<Utente> getUtentiFiltratiByNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> cr = cb.createQuery(Utente.class);
        Root<Utente> root = cr.from(Utente.class);
        cr.select(root).where(cb.like(root.get("nome"), "%"+nome+"%"));
        Query<Utente> query = session.createQuery(cr);
        List<Utente> results = query.getResultList();
        return  results;
    }

    public List<Utente> getUtentiFiltratiByCognome(String cognome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> cr = cb.createQuery(Utente.class);
        Root<Utente> root = cr.from(Utente.class);
        cr.select(root).where(cb.like(root.get("cognome"), "%"+cognome+"%"));
        Query<Utente> query = session.createQuery(cr);
        List<Utente> results = query.getResultList();
        return  results;
    }


}
