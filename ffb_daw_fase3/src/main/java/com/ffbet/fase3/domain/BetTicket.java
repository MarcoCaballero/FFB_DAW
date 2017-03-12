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
	private List<BetSportMatch> betMatchesList = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Promotion applied_promo;

	@Column(nullable = false)
	private int amount;

	@Column
	private double potentialGain;

	@Column
	private boolean isFinished;

	/**
	 * 
	 */
	public BetTicket() {
	}

	/**
	 * @param id
	 * @param matches_list
	 * @param applied_promo
	 * @param amount
	 * @param potential_gain
	 * @param isFinished
	 */
	public BetTicket(List<BetSportMatch> matches_list, Promotion applied_promo, int amount, int potential_gain,
			boolean isFinished) {
		this.betMatchesList = matches_list;
		this.applied_promo = applied_promo;
		this.amount = amount;
		this.potentialGain = potential_gain;
		this.isFinished = isFinished;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the matches_list
	 */
	public List<BetSportMatch> getBetMatches_list() {
		return betMatchesList;
	}

	/**
	 * @param matches_list
	 *            the matches_list to set
	 */
	public void setBetMatches_list(List<BetSportMatch> matches_list) {
		this.betMatchesList = matches_list;
	}

	/**
	 * @return the applied_promo
	 */
	public Promotion getApplied_promo() {
		return applied_promo;
	}

	/**
	 * @param applied_promo
	 *            the applied_promo to set
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
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the potential_gain
	 */
	public double getPotential_gain() {
		return potentialGain;
	}

	/**
	 * @param potential_gain
	 *            the potential_gain to set
	 */
	public void setPotential_gain(double potential_gain) {
		this.potentialGain = potential_gain;
	}

	/**
	 * @return the isFinished
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * @param isFinished
	 *            the isFinished to set
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**/

	public double calculatePotentialGain(double multiplicator) {
		double gain = 1;
		for (BetSportMatch bm : this.betMatchesList) {
			gain *= bm.getSelectedQuota();
		}
		gain *= multiplicator;
		return gain;
	}

	public void addMatchTeam(BetSportMatch match) {
		this.getBetMatches_list().add(match);
	}

	public boolean applyPromo(Promotion promo){
		if(promo.getType().equals(PromotionType.PROMO_GIFT.toString())){
			
		}else if(promo.getType().equals(PromotionType.PROMO_DISCCOUNT.toString())){
			
		}else{
			//Handle error;
		}
		return false;
		
	}

}
