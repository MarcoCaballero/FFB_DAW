/**
 * 
 */
package com.ffbet.fase3.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Marco
 *
 */
@Entity
public class BetSportMatch {

	/* COLUMNS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	protected long id;

	
	@OneToOne(cascade = CascadeType.ALL)
	private SportsMatch match;

	@Column(nullable = false)
	private boolean localSelected;

	private boolean drawSelected;

	@Column(nullable = false)
	private boolean visitingSelected;
	private String selectedNamequota;
	private double selectedQuota;

	/* CONSTRUCTORS */
	/**
	 * 
	 */
	public BetSportMatch() {
	}

	/**
	 * @param team
	 * @param localSelected
	 * @param drawSelected
	 * @param visitingSelected
	 */
	public BetSportMatch(SportsMatch match, boolean localSelected, boolean drawSelected, boolean visitingSelected) {
		this.match = match;
		this.localSelected = localSelected;
		this.drawSelected = drawSelected;
		this.visitingSelected = visitingSelected;
		if (localSelected){
			this.selectedNamequota = match.getHomeTeam();
			this.selectedQuota = match.getQuotaHomeVictory();
		}
		if (visitingSelected){
			this.selectedNamequota = match.getVisitingTeam();
			this.selectedQuota = match.getQuotaVisitingVictory();
		}
		if (drawSelected){
			this.selectedNamequota = "EMPATE";
			this.selectedQuota = match.getQuotaDraw();
		}
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
	 * @return the localSelected
	 */
	public boolean isLocalSelected() {
		return localSelected;
	}

	/**
	 * @param localSelected
	 *            the localSelected to set
	 */
	public void setLocalSelected(boolean localSelected) {
		this.localSelected = localSelected;
	}

	/**
	 * @return the drawSelected
	 */
	public boolean isDrawSelected() {
		return drawSelected;
	}

	/**
	 * @param drawSelected
	 *            the drawSelected to set
	 */
	public void setDrawSelected(boolean drawSelected) {
		this.drawSelected = drawSelected;
	}

	/**
	 * @return the visitingSelected
	 */
	public boolean isVisitingSelected() {
		return visitingSelected;
	}

	/**
	 * @param visitingSelected
	 *            the visitingSelected to set
	 */
	public void setVisitingSelected(boolean visitingSelected) {
		this.visitingSelected = visitingSelected;
	}

	/**
	 * @return the match
	 */
	public SportsMatch getMatch() {
		return match;
	}

	/**
	 * @param match the match to set
	 */
	public void setMatch(SportsMatch match) {
		this.match = match;
	}

	/**
	 * @return the selectedNamequota
	 */
	public String getSelectedNamequota() {
		return selectedNamequota;
	}

	/**
	 * @param selectedNamequota the selectedNamequota to set
	 */
	public void setSelectedNamequota(String selectedNamequota) {
		this.selectedNamequota = selectedNamequota;
	}

	/**
	 * @return the selectedQuota
	 */
	public double getSelectedQuota() {
		return selectedQuota;
	}

	/**
	 * @param selectedQuota the selectedQuota to set
	 */
	public void setSelectedQuota(double selectedQuota) {
		this.selectedQuota = selectedQuota;
	}
	
	

	/* GETTER & SETTER */

}
