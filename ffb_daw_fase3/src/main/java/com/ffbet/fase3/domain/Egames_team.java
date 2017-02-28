package com.ffbet.fase3.domain;

import javax.persistence.Entity;

/**
 * Entity object subclass {@link Egames_team} Defines an <i>eTeam</i> object by
 * extending an abstract {@link team}
 * 
 * @see {@link team}, {@link Sport_team}
 * @author Marco
 * @version 1.0
 */
@Entity
public class Egames_team extends Team {

	/* COLUMNS */
	private String sede;
	private String sponsor;

	/* CONSTRUCTORS */
	/**
	 * void constructor
	 */
	public Egames_team() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Contains all own parameters and all parameters from superclass
	 * 
	 * @param sede
	 * @param sponsor
	 * @param id
	 * @param name
	 * @param coach
	 * @param country
	 * @param city
	 */
	public Egames_team(String sede, String sponsor, long id, String name, String coach, String country, String city) {
		super(id, name, coach, country, city);
		this.sede = sede;
		this.sponsor = sponsor;
	}

	/**
	 * Contains all own parameters and the required parameters from superclass
	 * 
	 * @param sede
	 * @param sponsor
	 * @param id
	 * @param name
	 * @param coach
	 */
	public Egames_team(String sede, String sponsor, long id, String name, String coach) {
		super(id, name, coach);
		this.sede = sede;
		this.sponsor = sponsor;
	}

	/* GETTERS & SETTER */
	/**
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}

	/**
	 * @param sede
	 *            the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}

	/**
	 * @return the sponsor
	 */
	public String getSponsor() {
		return sponsor;
	}

	/**
	 * @param sponsor
	 *            the sponsor to set
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	/* OVERRIDE METHODS */

	// No one implemented yet..

	/* OWN METHODS */

	// No one implemented yet..

}
