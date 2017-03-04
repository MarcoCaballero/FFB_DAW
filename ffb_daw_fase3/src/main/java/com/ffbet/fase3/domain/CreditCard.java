package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is an entity class that defines tha table of the credit cards
 * 
 * @author Pedro
 * @version 1.0
 *
 */
@Entity
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( updatable = false, nullable = false)
	private long id;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surName;
	@Column(nullable = false)
	private String secondSurName;
	@Column(nullable = false)
	private int cardNumber;
	@Column(nullable = false)
	private String expirationDate;
	@Column(nullable = false)
	private int securityCode;
	
	// CONSTRUCTORS
	
	/**
	 * Void constructor
	 *
	 */
	public CreditCard() {
	}
	
	/**
	 * @param (required) type, the type of credit card
	 * @param (required) name, the name of the credit card's owner
	 * @param (required) surName, the surname of the credit card's owner
	 * @param (required) secondSurName, the second surname of the credit cards's owner
	 * @param (required) cardNumber, the number of credit card
	 * @param (required) expirationDate, the expiration date of credit card
	 * @param (required) securityCode, the security code of credit card
	 *
	 */
	public CreditCard(long id, String type, String name, String surName, String secondSurName, int cardNumber,
			String expirationDate, int securityCode) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.surName = surName;
		this.secondSurName = secondSurName;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getSecondSurName() {
		return secondSurName;
	}
	public void setSecondSurName(String secondSurName) {
		this.secondSurName = secondSurName;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

}
