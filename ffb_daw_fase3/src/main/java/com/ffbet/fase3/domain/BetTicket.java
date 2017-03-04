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
	public BetTicket(long id, List<Match> matches_list, Promotion applied_promo, int amount, int potential_gain,
			boolean isFinished) {
		this.id = id;
		this.matches_list = matches_list;
		this.applied_promo = applied_promo;
		this.amount = amount;
		this.potential_gain = potential_gain;
		this.isFinished = isFinished;
	}
	
	

}
