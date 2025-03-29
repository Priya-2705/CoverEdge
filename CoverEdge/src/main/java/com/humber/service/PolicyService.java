package com.humber.service;

import com.humber.model.BaseRate;
import com.humber.model.Customer;
import com.humber.model.Policy;
import com.humber.model.TermFactor;
import com.humber.repository.BaseRateRepository;
import com.humber.repository.PolicyRepository;
import com.humber.repository.TermFactorRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

public class PolicyService {
	
    private final PolicyRepository policyRepository = new PolicyRepository();
    private final BaseRateRepository baseRateRepo = new BaseRateRepository();
    private final TermFactorRepository termFactorRepo = new TermFactorRepository();

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
    
    public synchronized List<Policy> getRenewablePolicies() {
        return policyRepository.getRenewablePolicies();
    }

    public synchronized void renewPolicy(Policy policy, int renewalTermYears) throws Exception {
        try {
            System.out.println("Starting renewal for policy ID: " + policy.getPolicyId());
            
            // 1. Validate customer data
            Customer customer = policy.getCustomer();
            if (customer == null) {
                throw new Exception("Customer not associated with policy");
            }
            
            if (customer.getDateOfBirth() == null) {
                throw new Exception("Customer birthdate missing");
            }

            // 2. Calculate current age (fixed conversion)
            java.util.Date utilDob = new java.util.Date(customer.getDateOfBirth().getTime());
            LocalDate dob = utilDob.toInstant()
                                 .atZone(ZoneId.systemDefault())
                                 .toLocalDate();
            int age = Period.between(dob, LocalDate.now()).getYears();
            System.out.println("Calculated customer age: " + age);

            // 3. Get rate and factor
            BaseRate rate = baseRateRepo.getRateByAgeAndType(
                policy.getPolicyType(), 
                age
            );
            
            TermFactor factor = termFactorRepo.getFactorByTerm(
                renewalTermYears
            );

            if (rate == null) {
                throw new Exception("No rate found for " + policy.getPolicyType() 
                    + " policy and age " + age);
            }
            
            if (factor == null) {
                throw new Exception("No factor found for " + renewalTermYears 
                    + "-year term");
            }

            // 4. Calculate new premium
            double newPremium = rate.getRate() 
                              * policy.getCoverageAmount() 
                              * factor.getFactor();
            
            System.out.printf("Premium calculation: %.2f * %.2f * %.2f = %.2f%n",
                rate.getRate(),
                policy.getCoverageAmount(),
                factor.getFactor(),
                newPremium);

            // 5. Update policy dates (extend from existing end date)
            Calendar cal = Calendar.getInstance();
            cal.setTime(policy.getEndDate());
            cal.add(Calendar.YEAR, renewalTermYears);
            
            policy.setPremiumAmount(newPremium);
            policy.setEndDate(cal.getTime());
            
            System.out.println("New end date: " + policy.getEndDate());

            // 6. Save updated policy
            policyRepository.updatePolicy(policy);
            System.out.println("Renewal successful for policy " + policy.getPolicyId());
            
        } catch (Exception e) {
            System.err.println("Renewal failed for policy " + policy.getPolicyId());
            e.printStackTrace();
            throw new Exception("Renewal failed: " + e.getMessage(), e);
        }
    }
    
    public synchronized void cancelPolicy(int policyId, String reason) throws Exception {
        Policy policy = policyRepository.getPolicyById(policyId);
        if (policy == null) {
            throw new Exception("Policy not found");
        }
        if (policy.getPolicyStatus() != Policy.PolicyStatus.ACTIVE) {
            throw new Exception("Only active policies can be cancelled");
        }
        policyRepository.cancelPolicy(policyId, reason);
    }
}