package com.example.auto_park.hibernate.dao;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UtenteDAO {
    private HibernateAnnotationUtil HibernateUtil;
    public Utente getUtente(Long id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Utente.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    public boolean saveOrUpdateUtente(Utente c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(c);
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
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> cr = cb.createQuery(Utente.class);
        Root<Utente> root = cr.from(Utente.class);
        cr.select(root).where(cb.equal(root.get("tipo"), "customer"));
        Query<Utente> query = session.createQuery(cr);
        List<Utente> results = query.getResultList();
        return results;
    }

    public List<Utente> getUsersByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> cr = cb.createQuery(Utente.class);
        Root<Utente> root = cr.from(Utente.class);
        cr.select(root).where(cb.equal(root.get("username"), username));
        Query<Utente> query = session.createQuery(cr);
        List<Utente> results = query.getResultList();
        return  results;
    }

    public List<Utente> getCustomerByParam(String filtro, String testo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> cr = cb.createQuery(Utente.class);
        Root<Utente> root = cr.from(Utente.class);
        Predicate filtername = cb.like(root.get(filtro), "%"+testo+"%");
        Predicate customer = cb.like(root.get("tipo"), "customer");
        Predicate andN = cb.and(filtername,customer);
        cr.select(root).where(andN);
        Query<Utente> query = session.createQuery(cr);
        List<Utente> results = query.getResultList();
        return  results;
    }

}
