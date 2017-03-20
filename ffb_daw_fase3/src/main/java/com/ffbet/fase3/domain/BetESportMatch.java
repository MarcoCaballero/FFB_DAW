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
import javax.persistence.OneToOne;

/**
 * @author Marco
 *
 */
@Entity
public class BetESportMatch {

	/* COLUMNS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	protected long id;

	@OneToOne(cascade = CascadeType.ALL)
	private EgamesMatch match;

	@Column(nullable = false)
	private boolean localSelected;

	@Column(nullable = false)
	private boolean visitingSelected;

	@Column(nullable = false)
	private boolean localFirstBloodSelected;

	@Column(nullable = false)
	private boolean visitingFirstBloodSelected;

	private String selectedNamequota;
	private double selectedQuota;

	private String selectedFirstBloodNamequota;
	private double selectedFirstBloodQuota;

	/* CONSTRUCTORS */
	/**
	 * 
	 */
	public BetESportMatch() {
	}

	/**
	 * @param team
	 * @param localSelected
	 * @param drawSelected
	 * @param visitingSelected
	 */
	public BetESportMatch(EgamesMatch match, boolean localSelected, boolean visitingSelected,
			boolean localFirstBloodSelected, boolean visitingFirstBloodSelected) {
		this.match = match;
		this.localSelected = localSelected;
		this.visitingSelected = visitingSelected;
		this.localFirstBloodSelected = localFirstBloodSelected;
		this.visitingFirstBloodSelected = visitingFirstBloodSelected;
		if (this.localSelected) {
			this.selectedNamequota = match.getHomeTeam();
			this.selectedQuota = match.getQuotaHomeVictory();
		}
		if (this.visitingSelected) {
			this.selectedNamequota = match.getVisitingTeam();
			this.selectedQuota = match.getQuotaVisitingVictory();
		}
		if(this.localFirstBloodSelected){
			this.selectedFirstBloodNamequota = match.getVisitingTeam();
			this.selectedFirstBloodQuota = match.getQuotaHomeFirstBlood();
		}
		if(this.visitingFirstBloodSelected){
			this.selectedFirstBloodNamequota = match.getVisitingTeam();
			this.selectedFirstBloodQuota = match.getQuotaVisitingFirstBlood();
		}

	}
	
	/*GETER & SETTER*/

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
	 * @return the match
	 */
	public EgamesMatch getMatch() {
		return match;
	}

	/**
	 * @param match the match to set
	 */
	public void setMatch(EgamesMatch match) {
		this.match = match;
	}

	/**
	 * @return the localSelected
	 */
	public boolean isLocalSelected() {
		return localSelected;
	}

	/**
	 * @param localSelected the localSelected to set
	 */
	public void setLocalSelected(boolean localSelected) {
		this.localSelected = localSelected;
	}

	/**
	 * @return the visitingSelected
	 */
	public boolean isVisitingSelected() {
		return visitingSelected;
	}

	/**
	 * @param visitingSelected the visitingSelected to set
	 */
	public void setVisitingSelected(boolean visitingSelected) {
		this.visitingSelected = visitingSelected;
	}

	/**
	 * @return the localFirstBloodSelected
	 */
	public boolean isLocalFirstBloodSelected() {
		return localFirstBloodSelected;
	}

	/**
	 * @param localFirstBloodSelected the localFirstBloodSelected to set
	 */
	public void setLocalFirstBloodSelected(boolean localFirstBloodSelected) {
		this.localFirstBloodSelected = localFirstBloodSelected;
	}

	/**
	 * @return the visitingFirstBloodSelected
	 */
	public boolean isVisitingFirstBloodSelected() {
		return visitingFirstBloodSelected;
	}

	/**
	 * @param visitingFirstBloodSelected the visitingFirstBloodSelected to set
	 */
	public void setVisitingFirstBloodSelected(boolean visitingFirstBloodSelected) {
		this.visitingFirstBloodSelected = visitingFirstBloodSelected;
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

	/**
	 * @return the selectedFirstBloodNamequota
	 */
	public String getSelectedFirstBloodNamequota() {
		return selectedFirstBloodNamequota;
	}

	/**
	 * @param selectedFirstBloodNamequota the selectedFirstBloodNamequota to set
	 */
	public void setSelectedFirstBloodNamequota(String selectedFirstBloodNamequota) {
		this.selectedFirstBloodNamequota = selectedFirstBloodNamequota;
	}

	/**
	 * @return the selectedFirstBloodQuota
	 */
	public double getSelectedFirstBloodQuota() {
		return selectedFirstBloodQuota;
	}

	/**
	 * @param selectedFirstBloodQuota the selectedFirstBloodQuota to set
	 */
	public void setSelectedFirstBloodQuota(double selectedFirstBloodQuota) {
		this.selectedFirstBloodQuota = selectedFirstBloodQuota;
	}

	

}
