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
	private String cardNumber;
	@Column(nullable = false)
	private int expirationMonth;
	@Column(nullable = false)
	private int expirationYear;
	@Column(nullable = false)
	private int securityCode;
	@Column(nullable = false)
	private double credit;
	
	// CONSTRUCTORS
	
	/**
	 * Void constructor
	 *
	 */
	public CreditCard() {
	}
	
	

	/**
	 * @param id
	 * @param type
	 * @param name
	 * @param cardNumber
	 * @param expirationMonth
	 * @param expirationYear
	 * @param securityCode
	 * @param credit
	 */
	public CreditCard(String type, String name, String cardNumber, int expirationMonth, int expirationYear,
			int securityCode, double credit) {
		this.type = type;
		this.name = name;
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.securityCode = securityCode;
		this.credit = credit;
	}


	
	/**
	 * @param id
	 * @param type
	 * @param name
	 * @param cardNumber
	 * @param expirationMonth
	 * @param expirationYear
	 * @param securityCode
	 */
	public CreditCard(String type, String name, String cardNumber, int expirationMonth, int expirationYear,
			int securityCode) {
		this.type = type;
		this.name = name;
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.securityCode = securityCode;
		this.credit = 500.00;
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
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}



	/**
	 * @return the expirationMonth
	 */
	public int getExpirationMonth() {
		return expirationMonth;
	}



	/**
	 * @param expirationMonth the expirationMonth to set
	 */
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}



	/**
	 * @return the expirationYear
	 */
	public int getexpirationYear() {
		return expirationYear;
	}



	/**
	 * @param expirationYear the expirationYear to set
	 */
	public void setexpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	
	
	
	/**
	 * @return the expirationYear
	 */
	public int getExpirationYear() {
		return expirationYear;
	}



	/**
	 * @param expirationYear the expirationYear to set
	 */
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}



	/**
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}



	/**
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}



	public void sendMoney(double money){
		this.setCredit(this.getCredit()-money);
	}

}
