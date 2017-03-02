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
	@GeneratedValue(strategy = GenerationType.TABLE)
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
	private byte[] cover_image_faceboook;

	
}
