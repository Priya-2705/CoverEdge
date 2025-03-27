package com.humber.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Broker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer brokerId;
	private String firstname;
	private String lastname;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	private String phoneNumber;

	// Getters and setters
	public Integer getId() {
		return brokerId;
	}

	public void setId(Integer id) {
		this.brokerId = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}