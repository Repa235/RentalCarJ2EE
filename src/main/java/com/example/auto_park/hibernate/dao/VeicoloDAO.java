package com.example.auto_park.hibernate.dao;

import com.example.auto_park.hibernate.entity.Prenotazione;
import com.example.auto_park.hibernate.entity.Utente;
import com.example.auto_park.hibernate.entity.Veicolo;
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
import java.util.ArrayList;
import java.util.List;

public class VeicoloDAO {
    private HibernateAnnotationUtil HibernateUtil;

    public Veicolo getVeicolo(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Veicolo.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    public List<Veicolo> getVeicoli() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Veicolo> cr = cb.createQuery(Veicolo.class);
        Root<Veicolo> root = cr.from(Veicolo.class);
        cr.select(root);
        Query<Veicolo> query = session.createQuery(cr);
        List<Veicolo> results = query.getResultList();
        return results;
    }

    public boolean saveOrUpdateVeicolo(Veicolo c) {
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

    public List<Veicolo> getVeicoliLiberiNelRange(LocalDate dataSceltaI, LocalDate dataSceltaF) {
        //Prelevo la lista di prenotazioni approvate nel range
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Veicolo> cr = cb.createQuery(Veicolo.class);
        Root<Prenotazione> root = cr.from(Prenotazione.class);

        Predicate[] predicates = new Predicate[5];
        predicates[0] = cb.between(root.<LocalDate>get("dataInizio"), dataSceltaI, dataSceltaF);
        predicates[1] = cb.between(root.<LocalDate>get("dataFine"), dataSceltaI, dataSceltaF);
        predicates[2] = cb.equal(root.<Boolean>get("isApprovato"), true);
        predicates[3] = cb.lessThanOrEqualTo(root.<LocalDate>get("dataInizio"), dataSceltaI);
        predicates[4] = cb.greaterThan(root.<LocalDate>get("dataFine"), dataSceltaF);
        cr.select(root.get("veicolo")).where(predicates);

        Query<Veicolo> query = session.createQuery(cr);
        List<Veicolo> veicoliPrenotatiNelRange = query.getResultList();

        //Adesso che ho la lista dei veicoli prenotati nel range
        //mi prendo tutti i veicoli che non ricadono in questa lista
        List<Veicolo> veicoliLiberiNelRange = new ArrayList<Veicolo>();
        if (veicoliPrenotatiNelRange.isEmpty()) {
            return veicoliLiberiNelRange;
        } else {

            CriteriaBuilder cb2 = session.getCriteriaBuilder();
            CriteriaQuery<Veicolo> cr2 = cb.createQuery(Veicolo.class);
            Root<Veicolo> root2 = cr2.from(Veicolo.class);

            Predicate nonInLista = cb2.not(root.in(veicoliPrenotatiNelRange));
            cr2.select(root2).where(nonInLista);
            Query<Veicolo> query2 = session.createQuery(cr2);
            veicoliLiberiNelRange = query2.getResultList();

            return veicoliLiberiNelRange;
        }

    }
}
