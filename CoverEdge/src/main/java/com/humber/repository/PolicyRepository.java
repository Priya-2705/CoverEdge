package com.humber.repository;

import com.humber.model.Policy;
import com.humber.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PolicyRepository {
    
    public synchronized void savePolicy(Policy policy) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(policy);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public synchronized List<Policy> getAllPolicies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Policy", Policy.class).list();
        }
    }

    public synchronized Policy getPolicyById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Policy policy = session.get(Policy.class, id);
            if (policy == null) {
                System.out.println("DEBUG: No policy found with ID " + id);
            }
            return policy;
        }
    }

    public synchronized void updatePolicy(Policy policy) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(policy);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public synchronized void deletePolicy(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Policy policy = session.get(Policy.class, id);
            if (policy != null) session.remove(policy);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    
    public synchronized List<Policy> getRenewablePolicies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Policy> cq = cb.createQuery(Policy.class);
            Root<Policy> policy = cq.from(Policy.class);
            
            // Get current date and date 30 days from now
            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.add(Calendar.DATE, 30);
            Date thirtyDaysLater = cal.getTime();

            // Add debug logging
            System.out.println("DEBUG: Renewable policy query parameters:");
            System.out.println("Current time: " + now);
            System.out.println("30 days later: " + thirtyDaysLater);

            // Query construction
            cq.where(cb.and(
                cb.equal(policy.get("policyStatus"), Policy.PolicyStatus.ACTIVE),
                cb.between(policy.get("endDate"), now, thirtyDaysLater)
            ));

            List<Policy> results = session.createQuery(cq).list();
            
            // Log results
            System.out.println("DEBUG: Found " + results.size() + " renewable policies");
            results.forEach(p -> System.out.println(
                "Policy " + p.getPolicyNumber() + 
                " ends on " + p.getEndDate()
            ));
            
            return results;
        }
    }
    
    public synchronized void cancelPolicy(int policyId, String reason) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Policy policy = session.get(Policy.class, policyId);
            if (policy != null) {
                policy.setPolicyStatus(Policy.PolicyStatus.CANCELLED);
                policy.setCancellationReason(reason);
                session.merge(policy);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}