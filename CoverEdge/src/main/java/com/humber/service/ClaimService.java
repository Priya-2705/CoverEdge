package com.humber.service;

import com.humber.model.Claim;
import com.humber.repository.ClaimRepository;
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
}