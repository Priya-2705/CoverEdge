package com.humber.service;

import com.humber.model.Policy;
import com.humber.repository.PolicyRepository;
import java.util.List;

public class PolicyService {
    private final PolicyRepository policyRepository = new PolicyRepository();

    public synchronized void addPolicy(Policy policy) {
        policyRepository.savePolicy(policy);
    }

    public synchronized List<Policy> getAllPolicies() {
        return policyRepository.getAllPolicies();
    }

    public synchronized Policy getPolicy(int id) {
        return policyRepository.getPolicyById(id);
    }

    public synchronized void updatePolicy(Policy policy) {
        policyRepository.updatePolicy(policy);
    }

    public synchronized void deletePolicy(int id) {
        policyRepository.deletePolicy(id);
    }
    
    public synchronized void savePolicies(List<Policy> policies) {
        policyRepository.savePolicies(policies);
    }
}