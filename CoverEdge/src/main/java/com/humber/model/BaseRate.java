package com.humber.model;

import com.humber.model.Policy.PolicyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "base_rates")
public class BaseRate {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type", nullable = false)
    private Policy.PolicyType policyType;
    
    @Column(name = "min_age", nullable = false)
    private int minAge;
    
    @Column(name = "max_age", nullable = false)
    private int maxAge;
    
    @Column(nullable = false)
    private double rate;

    // Constructors
    public BaseRate() {}
    
    public BaseRate(Long id, PolicyType policyType, int minAge, int maxAge, double rate) {
		super();
		this.id = id;
		this.policyType = policyType;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.rate = rate;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Policy.PolicyType getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Policy.PolicyType policyType) {
		this.policyType = policyType;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}