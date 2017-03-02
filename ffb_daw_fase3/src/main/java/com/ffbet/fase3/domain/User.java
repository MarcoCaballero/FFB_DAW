package com.ffbet.fase3.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity object class {@link User} defines a object to manage the web users.
 * 
 * @see {@link UserFB}
 * @author Marco
 * @version 1.0
 */
@Entity
public class User {
	/* COLUMNS */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(updatable = false, nullable = false)
	protected long id;

	@OneToOne
	private UserFB user_FB_account;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	private String dni;

	@Column(nullable = false)
	private String e_mail;

	private int telephone;

	@Column(nullable = false)
	private String password;

	@Column
	private int country;

	@Column
	private int city;

	@Column
	private int location;

	@Column(length = 10000000)
	@Lob
	private byte[] profile_image;

	@OneToMany(cascade = CascadeType.ALL) // Unidirectional
	private List<BetTicket> bet_tickets;

	/* CONSTRUCTORS */

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * All own parameters constructor
	 * 
	 * @param name
	 * @param surname
	 * @param dni
	 * @param e_mail
	 * @param telephone
	 * @param password
	 * @param country
	 * @param city
	 * @param location
	 * @param profile_image
	 */
	public User(String name, String surname, String dni, String e_mail, int telephone, String password, int country,
			int city, int location, byte[] profile_image) {
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.e_mail = e_mail;
		this.telephone = telephone;
		this.password = password;
		this.country = country;
		this.city = city;
		this.location = location;
		this.profile_image = profile_image;
	}

	/**
	 * Only required parameters constructor
	 * 
	 * @param name
	 * @param surname
	 * @param dni
	 * @param e_mail
	 * @param password
	 */
	public User(String name, String surname, String dni, String e_mail, String password) {
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.e_mail = e_mail;
		this.password = password;
	}

	/* GETTERS & SETTER */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the e_mail
	 */
	public String getE_mail() {
		return e_mail;
	}

	/**
	 * @param e_mail
	 *            the e_mail to set
	 */
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	/**
	 * @return the telephone
	 */
	public int getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the country
	 */
	public int getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(int country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public int getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(int location) {
		this.location = location;
	}

	/**
	 * @return the profile_image
	 */
	public byte[] getProfile_image() {
		return profile_image;
	}

	/**
	 * @param profile_image
	 *            the profile_image to set
	 */
	public void setProfile_image(byte[] profile_image) {
		this.profile_image = profile_image;
	}

	/* OWN METHODS */

	// No one implemented yet..

}
