package com.humber.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "policies")
public class Policy {
    public enum PolicyType { HEALTH, AUTO, HOME, LIFE }
    public enum PolicyStatus { ACTIVE, EXPIRED, CANCELLED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private int policyId;

    @Column(name = "policy_number", unique = true)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type")
    private PolicyType policyType;

    @Column(name = "coverage_amount")
    private double coverageAmount;

    @Column(name = "premium_amount")
    private double premiumAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_status")
    private PolicyStatus policyStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    // Constructors
    public Policy() {
    	
    }
    
	public Policy(int policyId, String policyNumber, PolicyType policyType, double coverageAmount, double premiumAmount,
			Date startDate, Date endDate, Customer customer, PolicyStatus policyStatus, Broker broker) {
		super();
		this.policyId = policyId;
		this.policyNumber = policyNumber;
		this.policyType = policyType;
		this.coverageAmount = coverageAmount;
		this.premiumAmount = premiumAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customer = customer;
		this.policyStatus = policyStatus;
		this.broker = broker;
	}

	// Getters and Setters
	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}

	public double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}
}