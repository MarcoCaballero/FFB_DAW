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

	private double quotaDraw;
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
	 * @param quotaDraw,
	 *            the quota of draw's bet
	 * @param homePoints,
	 *            the points of the home team
	 * @param visitingPoints,
	 *            the points of the visiting team
	 */
	public SportsMatch(long id, Date date, Time time, String type, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory) {
		super(date, time, type, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
	}
	
	public SportsMatch(long id, Date date, Time time, String type, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory, double quotaDraw, int homePoints, int visitingPoints) {
		super(date, time, type, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
		this.quotaDraw = quotaDraw;
		this.homePoints = homePoints;
		this.visitingPoints = visitingPoints;
	}

	// GETTERS & SETTERS

	public double getQuotaDraw() {
		return quotaDraw;
	}

	public void setQuotaDraw(double quotaDraw) {
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
