package com.humber.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "claims")
public class Claim {
    public enum ClaimStatus { 
        PENDING, 
        APPROVED, 
        REJECTED 
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Column(name = "date_submitted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted;

    // Constructors
    public Claim() {
        // Default constructor required by Hibernate
    }

    public Claim(String description, ClaimStatus status, Policy policy, Date dateSubmitted) {
        this.description = description;
        this.status = status;
        this.policy = policy;
        this.dateSubmitted = dateSubmitted;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", policy=" + (policy != null ? policy.getPolicyId() : "null") +
                ", dateSubmitted=" + dateSubmitted +
                '}';
    }
}