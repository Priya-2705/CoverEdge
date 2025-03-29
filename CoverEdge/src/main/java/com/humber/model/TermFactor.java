package com.humber.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "term_factors")
public class TermFactor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "term_length", unique = true, nullable = false)
    private int termLength;
    
    @Column(nullable = false)
    private double factor;

    // Constructors
    public TermFactor() {
    	
    }
    
	public TermFactor(Long id, int termLength, double factor) {
		super();
		this.id = id;
		this.termLength = termLength;
		this.factor = factor;
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTermLength() {
		return termLength;
	}

	public void setTermLength(int termLength) {
		this.termLength = termLength;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
}