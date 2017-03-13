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

	@OneToMany(cascade = CascadeType.ALL)
	private List<BetSportMatch> betMatchesList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<BetESportMatch> betEspMatchesList = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Promotion applied_promo;

	@Column(nullable = false)
	private double amount;

	@Column
	private double potentialGain;

	@Column
	private boolean isFinished;

	@Column
	private boolean isWinned;

	@Column
	private boolean isLosed;

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
	public BetTicket(List<BetSportMatch> matches_list, Promotion applied_promo, double amount, int potential_gain,
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
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the betMatchesList
	 */
	public List<BetSportMatch> getBetMatchesList() {
		return betMatchesList;
	}

	/**
	 * @param betMatchesList
	 *            the betMatchesList to set
	 */
	public void setBetMatchesList(List<BetSportMatch> betMatchesList) {
		this.betMatchesList = betMatchesList;
	}

	/**
	 * @return the betEspMatchesList
	 */
	public List<BetESportMatch> getBetEspMatchesList() {
		return betEspMatchesList;
	}

	/**
	 * @param betEspMatchesList
	 *            the betEspMatchesList to set
	 */
	public void setBetEspMatchesList(List<BetESportMatch> betEspMatchesList) {
		this.betEspMatchesList = betEspMatchesList;
	}

	/**
	 * @return the potentialGain
	 */
	public double getPotentialGain() {
		return potentialGain;
	}

	/**
	 * @param potentialGain
	 *            the potentialGain to set
	 */
	public void setPotentialGain(double potentialGain) {
		this.potentialGain = Math.round(potentialGain * 100.00) / 100.00;
	}

	/**
	 * @return the isWinned
	 */
	public boolean isWinned() {
		return !this.isLosed && this.isFinished;
	}

	/**
	 * @param isWinned
	 *            the isWinned to set
	 */
	public void setWinned(boolean isWinned) {
		this.isWinned = isWinned;
	}

	/**
	 * @return the isLosed
	 */
	public boolean isLosed() {
		return isLosed;
	}

	/**
	 * @param isLosed
	 *            the isLosed to set
	 */
	public void setLosed(boolean isLosed) {
		this.isLosed = isLosed;
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

		for (BetESportMatch bm : this.betEspMatchesList) {
			gain *= bm.getSelectedQuota();
		}
		gain *= multiplicator;
		return gain;
	}

	public void addMatchTeam(BetSportMatch match) {
		this.getBetMatches_list().add(match);
	}

	public void addEMatchTeam(BetESportMatch match) {
		this.getBetEspMatchesList().add(match);
	}

	public boolean applyPromo(Promotion promo) {

		if (promo.getType().equals(PromotionType.PROMO_DISCCOUNT.toString())) {
			this.setAmount(promo.applyDiscount(this.getAmount()));
			return true;
		}
		return false;

	}

	public boolean checkTicket() {
		boolean isFinished = true;
		boolean isWinner = true;
		for (BetESportMatch bEm : betEspMatchesList) {
			if (bEm.getMatch().isFinished()) {
				if (!bEm.getMatch().getWinnerTeam(bEm.getSelectedNamequota())) {
					isWinner = false;
				}
				if (!bEm.getMatch().getFBWinnerTeam(bEm.getSelectedNamequota())) {
					isWinner = false;
				}
			}

		}
		for (BetSportMatch bSm : betMatchesList) {
			if (bSm.getMatch().isFinished()) {

				if (!bSm.getMatch().getWinnerTeam(bSm.getSelectedNamequota())) {
					isWinner = false;
				}
			}

		}

		return isFinished && isWinner;

	}

	public boolean checkFinishedTicket() {
		boolean isFinished = true;
		for (BetESportMatch bEm : betEspMatchesList) {
			if (!bEm.getMatch().isFinished())
				isFinished = false;

		}
		for (BetSportMatch bSm : betMatchesList) {
			if (!bSm.getMatch().isFinished())
				isFinished = false;

		}

		this.isFinished=isFinished;
		return isFinished;

	}
}
