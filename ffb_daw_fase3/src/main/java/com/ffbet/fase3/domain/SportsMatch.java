package com.ffbet.fase3.domain;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;

/**
 * This is a subclass that extends from Match and defines a sports match
 * 
 * @see {@link Match} {@link EgamesMatch}
 * @author Pedro
 * @version 1.0
 *
 */
@Entity
public class SportsMatch extends Match {

	//@Column(nullable = false)
	private int quotaDraw;
	private int homePoints;
	private int visitingPoints;

	// CONSTRUCTORS

	/**
	 * Void constructor
	 */
	public SportsMatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param (required)
	 *            quotaDraw, the quota of draw's bet
	 * @param (required)
	 *            homePoints, the points of the home team
	 * @param (required)
	 *            visitingPoints, the points of the visiting team
	 */
	public SportsMatch(long id, Date date, Time time, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory) {
		super(date, time, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
	}

	// GETTERS & SETTERS

	public int getQuotaDraw() {
		return quotaDraw;
	}

	public void setQuotaDraw(int quotaDraw) {
		this.quotaDraw = quotaDraw;
	}

	public int getHomePoints() {
		return homePoints;
	}

	public void setHomePoints(int homePoints) {
		this.homePoints = homePoints;
	}

	public int getVisitingPoints() {
		return visitingPoints;
	}

	public void setVisitingPoints(int visitingPoints) {
		this.visitingPoints = visitingPoints;
	}

}
