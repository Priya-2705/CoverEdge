package com.humber.service;

import com.humber.model.Claim;
import com.humber.model.Policy;
import java.util.List;
import java.util.stream.Collectors;

public class ReportService {
    private final CustomerService customerService;
    private final PolicyService policyService;
    private final ClaimService claimService;

    public ReportService() {
        this.customerService = new CustomerService();
        this.policyService = new PolicyService();
        this.claimService = new ClaimService();
    }

    public synchronized long getTotalCustomers() {
        return customerService.getAllCustomers().size();
    }

    public synchronized long getTotalPolicies() {
        return policyService.getAllPolicies().size();
    }

    public synchronized long getTotalClaims() {
        return claimService.getAllClaims().size();
    }

    public synchronized long getApprovedClaims() {
        return claimService.getAllClaims().stream()
                .filter(c -> c.getStatus() == Claim.ClaimStatus.APPROVED)
                .count();
    }

    public synchronized double getApprovalRate() {
        long total = getTotalClaims();
        return total > 0 ? (getApprovedClaims() * 100.0) / total : 0;
    }

    public synchronized List<Policy> getPoliciesByBroker(int brokerId) {
        return policyService.getAllPolicies().stream()
                .filter(p -> p.getBroker().getId() == brokerId)
                .collect(Collectors.toList());
    }
}