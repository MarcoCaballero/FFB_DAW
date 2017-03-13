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
	private double quotaHomeFirstBlood;
	@Column(nullable = false)
	private double quotaVisitingFirstBlood;
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

	public EgamesMatch(Date date, Time time, String type, EgamesTeam homeTeam, EgamesTeam visitingTeam,
			double quotaHomeVictory, double quotaVisitingVictory, double quotaHomeFirstBlood,
			double quotaVisitingFirstBlood) {
		super(date, time, type, homeTeam.getName(), visitingTeam.getName(), quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
		this.quotaHomeFirstBlood = quotaHomeFirstBlood;
		this.quotaVisitingFirstBlood = quotaVisitingFirstBlood;
		this.teams.add(homeTeam);
		this.teams.add(visitingTeam);
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
	public EgamesMatch(Date date, Time time, String type, String homeTeam, String visitingTeam, double quotaHomeVictory,
			double quotaVisitingVictory, double quotaHomeFirstBlood, double quotaVisitingFirstBlood, String winnerTeam,
			String firstBloodTeam) {
		super(date, time, type, homeTeam, visitingTeam, quotaHomeVictory, quotaVisitingVictory);
		// TODO Auto-generated constructor stub
		this.quotaHomeFirstBlood = quotaHomeFirstBlood;
		this.quotaVisitingFirstBlood = quotaVisitingFirstBlood;
		this.winnerTeam = winnerTeam;
		this.firstBloodTeam = firstBloodTeam;
	}

	public EgamesMatch(Date date, Time time, String type, double quotaHomeVictory, double quotaHomeFirstBlood, double quotaVisitingFirstBlood, String firstBloodTeam, String winnerTeam, double quotaVisitingVictory,  EgamesTeam localTeam,  EgamesTeam visitingTeam) {
		super(date, time, type, localTeam.getName(), visitingTeam.getName(), quotaHomeVictory, quotaVisitingVictory);
		this.teams.add(localTeam);
		this.teams.add(visitingTeam);
		this.quotaHomeFirstBlood=quotaHomeFirstBlood;
		this.quotaVisitingFirstBlood=quotaVisitingFirstBlood;
		this.firstBloodTeam=firstBloodTeam;
		this.winnerTeam=winnerTeam;
		// TODO Auto-generated constructor stub
	}
	
	// GETTERS & SETTERS

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

	public double getQuotaHomeFirstBlood() {
		return quotaHomeFirstBlood;
	}

	public void setQuotaHomeFirstBlood(double quotaHomeFirstBlood) {
		this.quotaHomeFirstBlood = quotaHomeFirstBlood;
	}

	public double getQuotaVisitingFirstBlood() {
		return quotaVisitingFirstBlood;
	}

	public void setQuotaVisitingFirstBlood(double quotaVisitingFirstBlood) {
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

	/**/

	public boolean getWinnerTeam(String nameWinner) {

		if (this.winHome) {
			if (nameWinner.equals(this.getTeams().get(0).getName())) {
				return true;
			}
		} else {
			if (nameWinner.equals(this.getTeams().get(1).getName())) {
				return true;
			}
		}

		return false;
	}

	public boolean getFBWinnerTeam(String nameWinner) {

		if (this.isFirstBloodHome()) {
			if (nameWinner.equals(this.getTeams().get(0).getName())) {
				return true;
			}
		} else {
			if (nameWinner.equals(this.getTeams().get(1).getName())) {
				return true;
			}
		}

		return false;
	}

}
