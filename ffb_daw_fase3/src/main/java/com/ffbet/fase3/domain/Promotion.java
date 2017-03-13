package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is an entity class that defines the table of the promotions.
 * 
 * @author Pedro
 * @version 1.0
 *
 */
@Entity
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String title;
	private String description;
	@Column(nullable = false)
	private String promotionCode;
	private String promotionImage;

	@Column(nullable = false)
	private int quantity;

	private boolean shown;

	// CONSTRUCTORS

	/**
	 * Void constructor
	 *
	 */
	public Promotion() {
	}

	/**
	 * @param (required)
	 *            type, the type of promotion
	 * @param (required)
	 *            title, the title of promotion
	 * @param (required)
	 *            promotionCode, the code of promotion
	 *
	 */
	public Promotion(String type, String title, String promotionCode, int quantity) {
		this.type = type;
		this.title = title;
		this.promotionCode = promotionCode;
		this.quantity = quantity;
	}
	
	public Promotion(String type, String title, String description, String promotionCode, int quantity) {
		this.type = type;
		this.title = title;
		this.description = description;
		this.promotionCode = promotionCode;
		this.quantity = quantity;
	}

	public Promotion(String type, String title, String description, String promotionCode, int quantity, String promotionImage) {
		this.type = type;
		this.title = title;
		this.description = description;
		this.promotionCode = promotionCode;
		this.quantity = quantity;
		this.promotionImage = promotionImage;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPromotionImage() {
		return promotionImage;
	}

	public void setPromotionImage(String promotionImage) {
		this.promotionImage = promotionImage;
	}

	/**
	 * @return the shown
	 */
	public boolean isShown() {
		return shown;
	}

	/**
	 * @param shown
	 *            the shown to set
	 */
	public void setShown(boolean shown) {
		this.shown = shown;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**/

	public double applyDiscount(double amount) {
		double percentage = (double) this.getQuantity() / 100;
		double substAmount = amount * percentage;

		return amount-substAmount;

	}

}
