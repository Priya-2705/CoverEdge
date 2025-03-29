package com.humber.repository;

import com.humber.model.Claim;
import com.humber.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ClaimRepository {
    
    public synchronized void saveClaim(Claim claim) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(claim);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public synchronized List<Claim> getAllClaims() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Claim", Claim.class).list();
        }
    }

    public synchronized void updateClaimStatus(int claimId, String status) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Claim claim = session.get(Claim.class, claimId);
            if (claim != null) {
                claim.setStatus(Claim.ClaimStatus.valueOf(status));
                session.merge(claim);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}