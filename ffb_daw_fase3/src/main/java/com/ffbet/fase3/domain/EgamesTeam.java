package com.ffbet.fase3.domain;

import javax.persistence.Entity;

/**
 * Entity object subclass {@link EgamesTeam} Defines an <i>eTeam</i> object by
 * extending an abstract {@link team}
 * 
 * @see {@link team}, {@link SportTeam}
 * @author Marco
 * @version 1.0
 */
@Entity
public class EgamesTeam extends Team {

	/* COLUMNS */
	private String sponsor;

	/* CONSTRUCTORS */
	/**
	 * void constructor
	 */
	public EgamesTeam() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Contains all own parameters and all parameters from superclass
	 * 
	 * @param sponsor
	 * @param id
	 * @param name
	 * @param coach
	 * @param country
	 * @param city
	 */
	public EgamesTeam(String type, String sponsor, long id, String name, String coach, String country, String city) {
		super(name, coach, country, city, type);
		this.sponsor = sponsor;
	}

	/**
	 * Contains all own parameters and the required parameters from superclass
	 * 
	 * @param sponsor
	 * @param id
	 * @param name
	 * @param coach
	 */
	public EgamesTeam(String type, String sponsor, long id, String name, String coach) {
		super(name, coach,type);
		this.sponsor = sponsor;
	}

	/* GETTERS & SETTER */
	
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
