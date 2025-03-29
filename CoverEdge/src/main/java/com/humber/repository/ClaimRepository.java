package com.humber.repository;

import com.humber.model.Claim;
import com.humber.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
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
    
    public List<Claim> getClaimsByPolicy(int policyId, Date startDate, Date endDate, String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
            Root<Claim> claim = cq.from(Claim.class);

            List<Predicate> predicates = new ArrayList<>();
            
            // Mandatory policy filter
            predicates.add(cb.equal(claim.get("policy").get("policyId"), policyId));
            
            // Date range filter
            if(startDate != null && endDate != null) {
                predicates.add(cb.between(claim.get("dateSubmitted"), startDate, endDate));
            }
            
            // Status filter
            if(status != null && !status.isEmpty()) {
                predicates.add(cb.equal(claim.get("status"), Claim.ClaimStatus.valueOf(status)));
            }
            
            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.desc(claim.get("dateSubmitted")));
            
            return session.createQuery(cq).getResultList();
        }
    }
}