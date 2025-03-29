package com.humber.service;

import com.humber.model.BaseRate;
import com.humber.model.Policy;
import com.humber.model.TermFactor;
import com.humber.repository.BaseRateRepository;
import com.humber.repository.TermFactorRepository;

public class PolicyQuoteService {
	
	private final BaseRateRepository baseRateRepo = new BaseRateRepository();
	 private final TermFactorRepository termFactorRepo = new TermFactorRepository();
	
	 public synchronized double calculateQuote(Policy.PolicyType type, int age, 
	         double coverageAmount, int termYears) throws Exception {
	     
	     BaseRate rate = baseRateRepo.getRateByAgeAndType(type, age);
	     TermFactor factor = termFactorRepo.getFactorByTerm(termYears);
	     
	     if(rate == null || factor == null) {
	         throw new Exception("Could not find matching rate or factor");
	     }
	     
	     return rate.getRate() * coverageAmount * factor.getFactor();
	 }
}