package com.humber.repository;

import org.hibernate.Session;

import com.humber.model.TermFactor;
import com.humber.util.HibernateUtil;

public class TermFactorRepository {
    public synchronized TermFactor getFactorByTerm(int termLength) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM TermFactor WHERE termLength = :term", 
                TermFactor.class)
                .setParameter("term", termLength)
                .uniqueResult();
        }
    }
}