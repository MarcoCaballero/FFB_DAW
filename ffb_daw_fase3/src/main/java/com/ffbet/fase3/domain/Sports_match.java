package com.ffbet.fase3.domain;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * This is a subclass that extends from Match and defines a sports match
 * 
 * @see {@link Match} {@link Egames_match}
 * @author Pedro
 * @version 1.0
 *
 */
@Entity
public class Sports_match extends Match {

	@Column(nullable = false)
	private int quotaDraw;
	@Column(nullable = false)
	private int homePoints;
	@Column(nullable = false)
	private int visitingPoints;

	// CONSTRUCTORS

	/**
	 * Void constructor
	 */
	public Sports_match() {
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
	public Sports_match(long id, Date date, Time time, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory) {
		super(id, date, time, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
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
