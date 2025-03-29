package com.humber.model;

import jakarta.persistence.*;

@Entity
@Table(name = "brokers")
public class Broker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    // Constructors
    public Broker() {
    	
    }

	public Broker(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

    // Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}