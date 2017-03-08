package com.ffbet.fase3.domain;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * This is a subclass that extends from Match and defines an e-games match
 * @see {@link Match} {@link Sports_match}
 * @author Pedro
 * @version 1.0
 */

/**
 * @author Pedro
 *
 */
@Entity
public class EgamesMatch extends Match {

	@Column(nullable = false)
	private int quotaHomeFirstBlood;
	@Column(nullable = false)
	private int quotaVisitingFirstBlood;
	private String winnerTeam;
	private String firstBloodTeam;
	private boolean winHome;
	private boolean firstBloodHome;
	private boolean winVisiting;
	private boolean firstBloodVisiting;

	// CONSTRUCTORS

	/**
	 * Void constructor
	 */
	public EgamesMatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The params from superclass
	 * 
	 * @param (required)
	 *            quotaHomeFirstBlood, the quota of home first blood's bet
	 * @param (required)
	 *            quotaVisitingFirstBlood, the quota of visiting first blood's
	 *            bet
	 */
	public EgamesMatch(long id, Date date, Time time, String type, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory, int quotaHomeFirstBlood, int quotaVisitingFirstBlood) {
		super(date, time, type, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
		this.quotaHomeFirstBlood = quotaHomeFirstBlood;
		this.quotaVisitingFirstBlood = quotaVisitingFirstBlood;
	}

	/**
	 * The params from superclass
	 * 
	 * @param (required)
	 *            quotaHomeFirstBlood, the quota of home first blood's bet
	 * @param (required)
	 *            quotaVisitingFirstBlood, the quota of visiting first blood's
	 *            bet
	 * @param winnerTeam,
	 *            the team who wins
	 * @param firstBloodTeam,
	 *            the team who makes the first blood
	 */
	public EgamesMatch(long id, Date date, Time time,String type, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory, int quotaHomeFirstBlood, int quotaVisitingFirstBlood, String winnerTeam,
			String firstBloodTeam) {
		super(date, time, type, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
		this.quotaHomeFirstBlood = quotaHomeFirstBlood;
		this.quotaVisitingFirstBlood = quotaVisitingFirstBlood;
		this.winnerTeam = winnerTeam;
		this.firstBloodTeam = firstBloodTeam;
	}

	// GETTERS & SETTERS
 
	
	public int getQuotaHomeFirstBlood() {
		return quotaHomeFirstBlood;
	}

	public boolean isWinVisiting() {
		return winVisiting;
	}

	public void setWinVisiting(boolean winVisiting) {
		this.winVisiting = winVisiting;
	}

	public boolean isFirstBloodVisiting() {
		return firstBloodVisiting;
	}

	public void setFirstBloodVisiting(boolean firstBloodVisiting) {
		this.firstBloodVisiting = firstBloodVisiting;
	}

	public boolean isWinHome() {
		return winHome;
	}

	public void setWinHome(boolean winHome) {
		this.winHome = winHome;
	}

	public boolean isFirstBloodHome() {
		return firstBloodHome;
	}

	public void setFirstBloodHome(boolean firstBloodHome) {
		this.firstBloodHome = firstBloodHome;
	}

	public void setQuotaHomeFirstBlood(int quotaHomeFirstBlood) {
		this.quotaHomeFirstBlood = quotaHomeFirstBlood;
	}

	public int getQuotaVisitingFirstBlood() {
		return quotaVisitingFirstBlood;
	}

	public void setQuotaVisitingFirstBlood(int quotaVisitingFirstBlood) {
		this.quotaVisitingFirstBlood = quotaVisitingFirstBlood;
	}

	public String getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

	public String getFirstBloodTeam() {
		return firstBloodTeam;
	}

	public void setFirstBloodTeam(String firstBloodTeam) {
		this.firstBloodTeam = firstBloodTeam;
	}

}
