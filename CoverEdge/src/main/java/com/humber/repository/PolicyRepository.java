package com.humber.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.humber.model.Policy;
import com.humber.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PolicyRepository {
    
	private static final String POLICY_FILE = "policies.json";
	
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
        try {
            String json = new String(Files.readAllBytes(Paths.get(POLICY_FILE)));
            return new Gson().fromJson(json, new TypeToken<List<Policy>>(){}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
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
    
    public synchronized void savePolicies(List<Policy> policies) {
        try (FileWriter writer = new FileWriter(POLICY_FILE)) {
            new Gson().toJson(policies, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}