package com.ffbet.fase3.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private String email;

	private int telephone;

	@Column(nullable = false)
	private String password;

	@Column
	private String country;

	@Column
	private String city;

	@Column
	private String location;

	@Column(length = 10000000)
	@Lob
	private byte[] profile_image;

	@OneToMany(cascade = CascadeType.ALL) // Unidirectional
	private List<BetTicket> bet_tickets = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL) // Unidirectional
	private List<Promotion> promos = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	private boolean isMen;
	private boolean isPhotoSelected;
	
	private double credit;

	/* CONSTRUCTORS */

	/**
	 * 
	 */
	public User() {
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
	public User(String name, String surname, String dni, String email, int telephone, String password, String country,
			String city, String location, byte[] profile_image, String... roles) {
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.email = email;
		this.telephone = telephone;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
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
	public User(String name, String surname, String dni, String email, String password, boolean isMen, String... roles) {
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.email = email;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.isMen = isMen;
		this.isPhotoSelected = false;
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
	public String getEmail() {
		return email;
	}

	/**
	 * @param e_mail
	 *            the e_mail to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
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
	
	/**
	 * @return
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 */
	public void setRoles(String... roles) {
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user_FB_account
	 */
	public UserFB getUser_FB_account() {
		return user_FB_account;
	}

	/**
	 * @param user_FB_account the user_FB_account to set
	 */
	public void setUser_FB_account(UserFB user_FB_account) {
		this.user_FB_account = user_FB_account;
	}

	/**
	 * @return the bet_tickets
	 */
	public List<BetTicket> getBet_tickets() {
		return bet_tickets;
	}

	/**
	 * @param bet_tickets the bet_tickets to set
	 */
	public void setBet_tickets(List<BetTicket> bet_tickets) {
		this.bet_tickets = bet_tickets;
	}

	/**
	 * @return the promos
	 */
	public List<Promotion> getPromos() {
		return promos;
	}

	/**
	 * @param promos the promos to set
	 */
	public void setPromos(List<Promotion> promos) {
		this.promos = promos;
	}

	/**
	 * @return the isMen
	 */
	public boolean isMen() {
		return isMen;
	}

	/**
	 * @param isMen the isMen to set
	 */
	public void setMen(boolean isMen) {
		this.isMen = isMen;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the isPhotoSelected
	 */
	public boolean isPhotoSelected() {
		return isPhotoSelected;
	}

	/**
	 * @param isPhotoSelected the isPhotoSelected to set
	 */
	public void setPhotoSelected(boolean isPhotoSelected) {
		this.isPhotoSelected = isPhotoSelected;
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


	/* OWN METHODS */

	// No one implemented yet..

}
