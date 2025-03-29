package com.humber.repository;

import com.humber.model.Broker;
import com.humber.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class BrokerRepository {
    public synchronized List<Broker> getAllBrokers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Broker", Broker.class).list();
        }
    }
}