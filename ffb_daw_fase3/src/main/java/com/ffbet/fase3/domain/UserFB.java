package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.social.facebook.api.Facebook;

/**
 * Entity object class {@link UserFB} defines a object to manage the web clients
 * from {@link Facebook} API.
 * 
 * @see {@link User}, {@link Facebook}
 * @author Marco
 * @version 1.0
 */
@Entity
public class UserFB {


	/* COLUMNS */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	protected long id_FB;
	
	@OneToOne(mappedBy = "user_FB_account")
	private User main_user_account;

	@Column(nullable = false)
	private String name_FB;

	@Column(nullable = false)
	private String surname_FB;

	@Column(nullable = false)
	private String e_mail_FB;

	@Column
	private int location_FB;

	@Column(length = 10000000)
	@Lob
	private byte[] profile_image_facebook;
	
	@Column(length = 10000000)
	@Lob
	private byte[] cover_image_faceboook;/*CONSTRUCTORS*/
	
	
	/*GETTERS & SETTERS*/

	/**
	 * @return the id_FB
	 */
	public long getId_FB() {
		return id_FB;
	}

	/**
	 * @param id_FB the id_FB to set
	 */
	public void setId_FB(long id_FB) {
		this.id_FB = id_FB;
	}

	/**
	 * @return the main_user_account
	 */
	public User getMain_user_account() {
		return main_user_account;
	}

	/**
	 * @param main_user_account the main_user_account to set
	 */
	public void setMain_user_account(User main_user_account) {
		this.main_user_account = main_user_account;
	}

	/**
	 * @return the name_FB
	 */
	public String getName_FB() {
		return name_FB;
	}

	/**
	 * @param name_FB the name_FB to set
	 */
	public void setName_FB(String name_FB) {
		this.name_FB = name_FB;
	}

	/**
	 * @return the surname_FB
	 */
	public String getSurname_FB() {
		return surname_FB;
	}

	/**
	 * @param surname_FB the surname_FB to set
	 */
	public void setSurname_FB(String surname_FB) {
		this.surname_FB = surname_FB;
	}

	/**
	 * @return the e_mail_FB
	 */
	public String getE_mail_FB() {
		return e_mail_FB;
	}

	/**
	 * @param e_mail_FB the e_mail_FB to set
	 */
	public void setE_mail_FB(String e_mail_FB) {
		this.e_mail_FB = e_mail_FB;
	}

	/**
	 * @return the location_FB
	 */
	public int getLocation_FB() {
		return location_FB;
	}

	/**
	 * @param location_FB the location_FB to set
	 */
	public void setLocation_FB(int location_FB) {
		this.location_FB = location_FB;
	}

	/**
	 * @return the profile_image_facebook
	 */
	public byte[] getProfile_image_facebook() {
		return profile_image_facebook;
	}

	/**
	 * @param profile_image_facebook the profile_image_facebook to set
	 */
	public void setProfile_image_facebook(byte[] profile_image_facebook) {
		this.profile_image_facebook = profile_image_facebook;
	}

	/**
	 * @return the cover_image_faceboook
	 */
	public byte[] getCover_image_faceboook() {
		return cover_image_faceboook;
	}

	/**
	 * @param cover_image_faceboook the cover_image_faceboook to set
	 */
	public void setCover_image_faceboook(byte[] cover_image_faceboook) {
		this.cover_image_faceboook = cover_image_faceboook;
	}
	
	
	

	
}
