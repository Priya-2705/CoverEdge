package com.humber.repository;

import org.hibernate.Session;

import com.humber.model.BaseRate;
import com.humber.model.Policy;
import com.humber.util.HibernateUtil;

public class BaseRateRepository {
	
    public synchronized BaseRate getRateByAgeAndType(Policy.PolicyType type, int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM BaseRate WHERE policyType = :type AND minAge <= :age AND maxAge >= :age", 
                BaseRate.class)
                .setParameter("type", type)
                .setParameter("age", age)
                .uniqueResult();
        }
    }
}