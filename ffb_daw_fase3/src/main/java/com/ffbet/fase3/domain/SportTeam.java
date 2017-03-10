package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Entity object subclass {@link SportTeam} defines an Sport team object by
 * extending an abstract {@link team} and provides methods to manage the
 * {@link SportTeam}.
 * 
 * @see {@link team}, {@link EgamesTeam}
 * @author Marco
 * @version 1.0
 */
@Entity
public class SportTeam extends Team {

	/* COLUMNS */
	private String slogan;

	private String stadium;

	private String president;

	private int leagues;

	private int cups;

	private int champions;

	@Column
	private String stadium_image;

	@Column
	private String logo_image;

	private String twitter_Uri;
	private String facebook_Uri;
	private String google_Uri;

	/* CONSTRUCTORS */

	/**
	 * Void constructor
	 */
	public SportTeam() {
		super();
	}

	/**
	 * @param stadium
	 * @param president
	 */
	public SportTeam(String stadium, String president, String name, String coach, String type) {
		super(name, coach, type);
		this.stadium = stadium;
		this.president = president;
	}

	/**
	 * Contains all own parameters and all parameters from superclass
	 * 
	 * @param slogan
	 * @param stadium
	 * @param president
	 * @param leagues
	 * @param cups
	 * @param champions
	 * @param league_position
	 * @param stadium_image
	 * @param logo_image
	 * @param twitter_Uri
	 * @param facebook_Uri
	 * @param google_Uri
	 * @param id
	 * @param name
	 * @param coach
	 * @param country
	 * @param city
	 */
	public SportTeam(String slogan, String stadium, String type, String president, int leagues, int cups, int champions,
			String stadium_image, String logo_image, String twitter_Uri, String facebook_Uri, String google_Uri,
			String name, String coach, String country, String city) {
		super(name, coach, country, city, type);
		this.slogan = slogan;
		this.stadium = stadium;
		this.president = president;
		this.leagues = leagues;
		this.cups = cups;
		this.champions = champions;
		this.stadium_image = stadium_image;
		this.logo_image = logo_image;
		this.twitter_Uri = twitter_Uri;
		this.facebook_Uri = facebook_Uri;
		this.google_Uri = google_Uri;
	}

	/**
	 * 
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
	 * @param logo_image
	 * @param twitter_Uri
	 * @param facebook_Uri
	 * @param google_Uri
	 * @param id
	 * @param name
	 * @param coach
	 */
	public SportTeam(String slogan, String stadium, String president, int leagues, int cups, int champions,
			String stadium_image, String logo_image, String twitter_Uri, String facebook_Uri, String google_Uri,
			String name, String coach, String type) {
		super(name, coach, type);
		this.slogan = slogan;
		this.stadium = stadium;
		this.president = president;
		this.leagues = leagues;
		this.cups = cups;
		this.champions = champions;
		this.stadium_image = stadium_image;
		this.logo_image = logo_image;
		this.twitter_Uri = twitter_Uri;
		this.facebook_Uri = facebook_Uri;
		this.google_Uri = google_Uri;
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
	 * @return the stadium_image
	 */
	public String getStadium_image() {
		return stadium_image;
	}

	/**
	 * @param stadium_image
	 *            the stadium_image to set
	 */
	public void setStadium_image(String stadium_image) {
		this.stadium_image = stadium_image;
	}

	/**
	 * @return the logo_image
	 */
	public String getLogo_image() {
		return logo_image;
	}

	/**
	 * @param logo_image
	 *            the logo_image to set
	 */
	public void setLogo_image(String logo_image) {
		this.logo_image = logo_image;
	}

	/**
	 * @return the twitter_Uri
	 */
	public String getTwitter_Uri() {
		return twitter_Uri;
	}

	/**
	 * @param twitter_Uri
	 *            the twitter_Uri to set
	 */
	public void setTwitter_Uri(String twitter_Uri) {
		this.twitter_Uri = twitter_Uri;
	}

	/**
	 * @return the facebook_Uri
	 */
	public String getFacebook_Uri() {
		return facebook_Uri;
	}

	/**
	 * @param facebook_Uri
	 *            the facebook_Uri to set
	 */
	public void setFacebook_Uri(String facebook_Uri) {
		this.facebook_Uri = facebook_Uri;
	}

	/**
	 * @return the google_Uri
	 */
	public String getGoogle_Uri() {
		return google_Uri;
	}

	/**
	 * @param google_Uri
	 *            the google_Uri to set
	 */
	public void setGoogle_Uri(String google_Uri) {
		this.google_Uri = google_Uri;
	}

	/* OVERRIDE METHODS */

	// No one implemented yet..

	/* OWN METHODS */

	// No one implemented yet..

}
