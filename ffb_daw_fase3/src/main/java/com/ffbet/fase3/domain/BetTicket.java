/**
 * 
 */
package com.ffbet.fase3.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity object class {@link BetTicket} defines a object to manage the web
 * bets. Includes prefixed {@link Amount}
 * 
 * @author Marco
 *
 */
@Entity
public class BetTicket {

	/* COLUMNS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	protected long id;

	@Column(nullable = false)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Match> matches_list = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Promotion applied_promo;

	@Column(nullable = false)
	private int amount;

	@Column(length = 100000)
	private int potential_gain;

	@Column
	private boolean isFinished;

	/**
	 * @param id
	 * @param matches_list
	 * @param applied_promo
	 * @param amount
	 * @param potential_gain
	 * @param isFinished
	 */
	public BetTicket(List<Match> matches_list, Promotion applied_promo, int amount, int potential_gain,
			boolean isFinished) {
		this.matches_list = matches_list;
		this.applied_promo = applied_promo;
		this.amount = amount;
		this.potential_gain = potential_gain;
		this.isFinished = isFinished;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the matches_list
	 */
	public List<Match> getMatches_list() {
		return matches_list;
	}

	/**
	 * @param matches_list the matches_list to set
	 */
	public void setMatches_list(List<Match> matches_list) {
		this.matches_list = matches_list;
	}

	/**
	 * @return the applied_promo
	 */
	public Promotion getApplied_promo() {
		return applied_promo;
	}

	/**
	 * @param applied_promo the applied_promo to set
	 */
	public void setApplied_promo(Promotion applied_promo) {
		this.applied_promo = applied_promo;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the potential_gain
	 */
	public int getPotential_gain() {
		return potential_gain;
	}

	/**
	 * @param potential_gain the potential_gain to set
	 */
	public void setPotential_gain(int potential_gain) {
		this.potential_gain = potential_gain;
	}

	/**
	 * @return the isFinished
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * @param isFinished the isFinished to set
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	
	
	
	
	

}
