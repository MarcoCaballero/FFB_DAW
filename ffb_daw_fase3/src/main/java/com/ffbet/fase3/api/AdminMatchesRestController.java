package com.ffbet.fase3.api;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.Match;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.services.MatchService;


@RestController
@RequestMapping("/api/matches")
public class AdminMatchesRestController {

	@Autowired
	private MatchService service;
	
	/* GET ALL MATCHES */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Match> getMatches() {
		return service.findAllMatches();
	}
	
	
	/* GET ALL SPORTS MATCH*/
	@RequestMapping(value = "/sports", method = RequestMethod.GET)
	public Collection<SportsMatch> getMatchesSports() {
		return service.findAllSports();
	}
	
	/* GET ALL EGAMES MATCH*/
	@RequestMapping(value = "/egames", method = RequestMethod.GET)
	public Collection<EgamesMatch> getMatchesEgames() {
		return service.findAllEgames();
	}
	
	/* GET SPORTS MATCH FIND BY Id*/
	@RequestMapping(value = "/sports/{id}", method = RequestMethod.GET)
	public ResponseEntity<SportsMatch> getSportMatch(@PathVariable long id) {

		SportsMatch match = service.findOneSports(id);
		if (match != null) {
			return new ResponseEntity<>(match, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* GET EGAMES MATCH FIND BY Id*/
	@RequestMapping(value = "/egames/{id}", method = RequestMethod.GET)
	public ResponseEntity<EgamesMatch> getEgamesMatch(@PathVariable long id) {
		
		EgamesMatch match = service.findOneEgames(id);
		if (match != null) {
			return new ResponseEntity<>(match, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* DELETE EGAMES MATCH FIND BY Id*/
	@RequestMapping(value = "/delete/egames/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EgamesMatch> deleteEgameMatch(@PathVariable long id) {

		
		EgamesMatch match = service.findOneEgames(id);
		service.delete(id);
		
		if (match != null){
			return new ResponseEntity<>(match, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* DELETE SPORTS MATCH FIND BY Id*/
	@RequestMapping(value = "/delete/sports/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<SportsMatch> deleteSportMatch(@PathVariable long id) {
		
		
		SportsMatch match = service.findOneSports(id);
		service.delete(id);
		
		if (match != null){
			return new ResponseEntity<>(match, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* PUT EGAMES MATCH FIND BY Id*/
	@RequestMapping(value =  "/update/egames/{id}", method = RequestMethod.PUT)
	public ResponseEntity<EgamesMatch> updateEgamesMatchByID(HttpServletRequest request, 
			 @PathVariable long id, @RequestBody EgamesMatch updateEgamesMatch) {
			
		EgamesMatch match =service.findOneEgames(id);
			
		if(match != null){
			updateEgamesMatch.setId(id);
			service.saveEgamesMatch(updateEgamesMatch);
			return new ResponseEntity<>(match, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	/* PUT SPORT MATCH FIND BY Id*/
	@RequestMapping(value =  "/update/sports/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SportsMatch> updateSportMatchByID(HttpServletRequest request, 
			 @PathVariable long id, @RequestBody SportsMatch updateSportsMatch) {
			
		SportsMatch match =service.findOneSports(id);
			
		if(match != null){
			updateSportsMatch.setId(id);
			service.saveSportsMatch(updateSportsMatch);
			return new ResponseEntity<>(match, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	/* POST A NEW SPORT MATCH*/
	@RequestMapping(value = "/new/sports", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public SportsMatch createSportMatch(@RequestBody SportsMatch match) {

		service.saveSportsMatch(match);

		return match;
	}
	
	/* POST A NEW EGAMES MATCH*/
	@RequestMapping(value = "/new/egames", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public EgamesMatch createEgamesMatch(@RequestBody EgamesMatch match) {

		service.saveEgamesMatch(match);

		return match;
	}


	

}
