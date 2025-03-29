package com.humber.service;

import com.humber.model.Claim;
import com.humber.repository.ClaimRepository;

import java.util.Date;
import java.util.List;

public class ClaimService {
    private final ClaimRepository claimRepository = new ClaimRepository();

    public synchronized void addClaim(Claim claim) {
        claimRepository.saveClaim(claim);
    }

    public synchronized List<Claim> getAllClaims() {
        return claimRepository.getAllClaims();
    }

    public synchronized void updateClaimStatus(int claimId, String status) {
        claimRepository.updateClaimStatus(claimId, status);
    }
    
    public List<Claim> getClaimsByPolicy(int policyId, Date startDate, Date endDate, String status) {
        return claimRepository.getClaimsByPolicy(policyId, startDate, endDate, status);
    }
}