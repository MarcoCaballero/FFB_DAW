package com.ffbet.fase3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
/**
 * Abstract parent class {@link Team}
 * Defines an abstract class that could be used by any child who extends it,
 * and provides variables and methods that could be wholly shared by all subclasses
 * 
 * @see {@link Sport_team}, {@link Egames_team}
 * @author Marco
 * @version 1.0
 * */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Team {

	//COLUMNS
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(updatable = false, nullable = false)
	protected long id;

	@Column(nullable = false)
	protected String name;
	
	
	protected String coach;
	
	protected String country;
	protected String city;
	
	
	//CONSTRUCTORS
	
	/**
	 * Void constructor
	 * */
	public Team() {}

	
	/**
	 * @param id (auto-generated by table strategy), team identifier.
	 * @param name, (required) name of the team.
	 * @param coach, (required) coach of the team.
	 * @param country, country of the team.
	 * @param city,  city of the team.
	 */
	public Team(long id, String name, String coach, String country, String city) {
		this.id = id;
		this.name = name;
		this.coach = coach;
		this.country = country;
		this.city = city;
	}
	
	/**
	 * @param id (auto-generated by table strategy), team identifier.
	 * @param name, (required) name of the team.
	 * @param coach, (required) coach of the team.
	 * @param country, country of the team.
	 * @param city,  city of the team.
	 */
	public Team(long id, String name, String coach) {
		this.id = id;
		this.name = name;
		this.coach = coach;
		this.country = "";
		this.city = "";
	}
	

	//GETTER & SETTER
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the coach
	 */
	public String getCoach() {
		return coach;
	}
	/**
	 * @param coach the coach to set
	 */
	public void setCoach(String coach) {
		this.coach = coach;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
		
	
	/*CLASS METHODS TO BE USED BY SUBCLASSES */
	

}
