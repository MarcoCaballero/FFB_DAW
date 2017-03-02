package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Entity object subclass {@link Sport_team} Defines an Sport team object by
 * extending an abstract {@link team}
 * 
 * @see {@link team}, {@link Egames_team}
 * @author Marco
 * @version 1.0
 */
@Entity
public class Sport_team extends Team {

	/* COLUMNS */
	private String slogan;

	private String stadium;

	private String president;

	private int leagues;

	private int cups;

	private int champions;

	private int league_position;

	@Column(length = 10000000)
	@Lob
	private byte[] stadium_image;

	@Column(length = 10000000)
	@Lob
	private byte[] shield_image;

	/* CONSTRUCTORS */
	

	/**
	 * Void constructor
	 */
	public Sport_team() {
		super();
	}

	/**
	 * Contains all own parameters and all parameters from superclass
	 * 
	 * @param id
	 * @param name
	 * @param coach
	 * @param country
	 * @param city
	 */
	public Sport_team(String slogan, String stadium, String president, int leagues, int cups, int champions,
			int league_position, byte[] stadium_image, byte[] shield_image, long id, String name, String coach,
			String country, String city) {
		super(id, name, coach, country, city);
		this.slogan = slogan;
		this.stadium = stadium;
		this.president = president;
		this.leagues = leagues;
		this.cups = cups;
		this.champions = champions;
		this.league_position = league_position;
		this.stadium_image = stadium_image;
		this.shield_image = shield_image;
	}

	/**
	 * Contains all own parameters and the required parameters from superclass
	 * 
	 * @param slogan
	 * @param stadium
	 * @param president
	 * @param leagues
	 * @param cups
	 * @param champions
	 * @param league_position
	 * @param stadium_image
	 * @param shield_image
	 */
	public Sport_team(String slogan, String stadium, String president, int leagues, int cups, int champions,
			int league_position, byte[] stadium_image, byte[] shield_image, long id, String name, String coach) {
		super(id, name, coach);
		this.slogan = slogan;
		this.stadium = stadium;
		this.president = president;
		this.leagues = leagues;
		this.cups = cups;
		this.champions = champions;
		this.league_position = league_position;
		this.stadium_image = stadium_image;
		this.shield_image = shield_image;
	}

	/* GETTERS & SETTER */
	/**
	 * @return the slogan
	 */
	public String getSlogan() {
		return slogan;
	}

	/**
	 * @param slogan
	 *            the slogan to set
	 */
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	/**
	 * @return the stadium
	 */
	public String getStadium() {
		return stadium;
	}

	/**
	 * @param stadium
	 *            the stadium to set
	 */
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	/**
	 * @return the president
	 */
	public String getPresident() {
		return president;
	}

	/**
	 * @param president
	 *            the president to set
	 */
	public void setPresident(String president) {
		this.president = president;
	}

	/**
	 * @return the leagues
	 */
	public int getLeagues() {
		return leagues;
	}

	/**
	 * @param leagues
	 *            the leagues to set
	 */
	public void setLeagues(int leagues) {
		this.leagues = leagues;
	}

	/**
	 * @return the cups
	 */
	public int getCups() {
		return cups;
	}

	/**
	 * @param cups
	 *            the cups to set
	 */
	public void setCups(int cups) {
		this.cups = cups;
	}

	/**
	 * @return the champions
	 */
	public int getChampions() {
		return champions;
	}

	/**
	 * @param champions
	 *            the champions to set
	 */
	public void setChampions(int champions) {
		this.champions = champions;
	}

	/**
	 * @return the league_position
	 */
	public int getLeague_position() {
		return league_position;
	}

	/**
	 * @param league_position
	 *            the league_position to set
	 */
	public void setLeague_position(int league_position) {
		this.league_position = league_position;
	}

	/**
	 * @return the stadium_image
	 */
	public byte[] getStadium_image() {
		return stadium_image;
	}

	/**
	 * @param stadium_image
	 *            the stadium_image to set
	 */
	public void setStadium_image(byte[] stadium_image) {
		this.stadium_image = stadium_image;
	}

	/**
	 * @return the shield_image
	 */
	public byte[] getShield_image() {
		return shield_image;
	}

	/**
	 * @param shield_image
	 *            the shield_image to set
	 */
	public void setShield_image(byte[] shield_image) {
		this.shield_image = shield_image;
	}

	/* OVERRIDE METHODS */

	// No one implemented yet..

	/* OWN METHODS */

	// No one implemented yet..

}
