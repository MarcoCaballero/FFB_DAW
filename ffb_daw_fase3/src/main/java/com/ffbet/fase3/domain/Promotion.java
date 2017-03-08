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
	@Column( updatable = false, nullable = false)
	private long id;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String title;
	private String description;
	@Column(nullable = false)
	private String promotionCode;
	private byte[] promotionImage;
	
	// CONSTRUCTORS
	
	/**
	 * Void constructor
	 *
	 */
	public Promotion() {
	}
	
	/**
	 * @param (required) type, the type of promotion
	 * @param (required) title, the title of promotion
	 * @param (required) promotionCode, the code of promotion
	 *
	 */
	public Promotion(String type, String title, String promotionCode) {
		this.type = type;
		this.title = title;
		this.promotionCode = promotionCode;
	}
	
	public Promotion(String type, String title, String description, String promotionCode) {
		this.type = type;
		this.title = title;
		this.description = description;
		this.promotionCode = promotionCode;
	}

	/**
	 * @param (required) type, the type of promotion
	 * @param (required) title, the title of promotion
	 * @param description, the description of promotion
	 * @param (required) promotionCode, the code of promotion
	 * @param promotionImage, the publicity image of the promotion
	 *
	 */
	public Promotion(long id, String type, String title, String description, String promotionCode,
			byte[] promotionImage) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.description = description;
		this.promotionCode = promotionCode;
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
	public byte[] getPromotionImage() {
		return promotionImage;
	}
	public void setPromotionImage(byte[] promotionImage) {
		this.promotionImage = promotionImage;
	}

}
