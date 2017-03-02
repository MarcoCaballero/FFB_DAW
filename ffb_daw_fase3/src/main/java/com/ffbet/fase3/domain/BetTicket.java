/**
 * 
 */
package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity object class {@link BetTicket} defines a object to manage the web bets.
 * Includes prefixed {@link Amount}
 * 
 * @author Marco
 *
 */
@Entity
public class BetTicket {

	/* COLUMNS */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(updatable = false, nullable = false)
	protected long id;

	// @Column(nullable = false)
	// @ManytoMany
	// List<Matches> matches_list

	// Promotion applied_promo

	@Column(nullable = false)
	Amount amount;

	@Column(length = 100000)
	int potential_gain;
	
	
	
	

}
