package com.humber.repository;

import com.humber.model.Policy;
import com.humber.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            return session.get(Policy.class, id);
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
}