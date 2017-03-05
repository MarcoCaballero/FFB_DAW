package com.ffbet.fase3.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;




/**
 * This is an abstract class that define the general attributes for any child
 * who extends it.
 * 
 * @see {@link SportsMatch} {@link EgamesMatch}
 * @author Pedro
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(updatable = false, nullable = false)
	protected long id;
	//@Column(nullable = false)
	@DateTimeFormat(pattern="mm-dd-yyyy")
	protected Date date;
	//@Column(nullable = false)
	protected Time time;
	//@Column(nullable = false)
	protected String homeTeam;
	//@Column(nullable = false)
	protected String visitingTeam;
	//@Column(nullable = false)
	protected int quotaHomeVictory;
	//@Column(nullable = false)
	protected int quotaVisitingVictory;
	@ManyToMany
	protected List<Team> teams = new ArrayList<>();
	

	// CONSTRUCTORS

	/**
	 * Void constructor
	 */
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 *            (auto-generated by table strategy), match identifier.
	 * @param (required)
	 *            date, match's date
	 * @param (required)
	 *            time, match's time
	 * @param (required)
	 *            homeTeam, home team's name
	 * @param (required)
	 *            visitingTeam, visiting team's name
	 * @param (required)
	 *            quotaHomeVictory, the quota of home victory's bet
	 * @param (required)
	 *            quotaVisitingVictory, the quota of visiting victory's bet
	 */
	public Match(Date date, Time time, String homeTeam, String visitingTeam, int quotaHomeVictory,
			int quotaVisitingVictory) {
		super();
		this.date = date;
		this.time = time;
		this.homeTeam = homeTeam;
		this.visitingTeam = visitingTeam;
		this.quotaHomeVictory = quotaHomeVictory;
		this.quotaVisitingVictory = quotaVisitingVictory;
	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getVisitingTeam() {
		return visitingTeam;
	}

	public void setVisitingTeam(String visitingTeam) {
		this.visitingTeam = visitingTeam;
	}

	public int getQuotaHomeVictory() {
		return quotaHomeVictory;
	}

	public void setQuotaHomeVictory(int quotaHomeVictory) {
		this.quotaHomeVictory = quotaHomeVictory;
	}

	public int getQuotaVisitingVictory() {
		return quotaVisitingVictory;
	}

	public void setQuotaVisitingVictory(int quotaVisitingVictory) {
		this.quotaVisitingVictory = quotaVisitingVictory;
	}
	
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
