package com.ffbet.fase3.api;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.Match;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.MatchService;


@RestController
@RequestMapping("/api/matches")
public class AdminMatchesRestController {

	@Autowired
	private MatchService service;

	@Autowired
	private BetTicketService betTicketService;
	
	/* GET ALL MATCHES */
	@GetMapping
	public Collection<Match> getMatches() {
		return service.findAllMatches();
	}
	
	
	/* GET ALL SPORTS MATCH*/
	@GetMapping("/sports")
	public Collection<SportsMatch> getMatchesSports() {
		return service.findAllSports();
	}
	
	/* GET ALL FOOTBALL MATCH*/
	@GetMapping("/sports/football")
	public Collection<SportsMatch> getMatchesFootball() {
		return service.findAllFootball();
	}
	
	/* GET ALL BASKETBALL MATCH*/
	@GetMapping("/sports/basketball")
	public Collection<SportsMatch> getMatchesBasketball() {
		return service.findAllBasketball();
	}
	
	/* GET ALL EGAMES MATCH*/
	@GetMapping("/egames")
	public Collection<EgamesMatch> getMatchesEgames() {
		return service.findAllEgames();
	}
	
	/* GET ALL LOL MATCH*/
	@GetMapping("/egames/lol")
	public Collection<EgamesMatch> getMatchesLol() {
		return service.findAllLol();
	}
	
	/* GET ALL CS:GO MATCH*/
	@GetMapping("/egames/cs")
	public Collection<EgamesMatch> getMatchesCs() {
		return service.findAllCs();
	}
	
	/* GET SPORTS MATCH FIND BY Id*/
	@GetMapping("/sports/{id}")
	public ResponseEntity<SportsMatch> getSportMatch(@PathVariable long id) {

		SportsMatch match = service.findOneSports(id);
		if (match != null) {
			return new ResponseEntity<>(match, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* GET EGAMES MATCH FIND BY Id*/
	@GetMapping("/egames/{id}")
	public ResponseEntity<EgamesMatch> getEgamesMatch(@PathVariable long id) {
		
		EgamesMatch match = service.findOneEgames(id);
		if (match != null) {
			return new ResponseEntity<>(match, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* DELETE MATCH FIND BY Id*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Match> deleteMatch(@PathVariable long id) {

		
		Match match = service.findOne(id);
		service.delete(id);
		
		if (match != null){
			return new ResponseEntity<>(match, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* PUT EGAMES MATCH FIND BY Id*/
	@PutMapping("/egames/{id}")
	public ResponseEntity<EgamesMatch> updateEgamesMatchByID(HttpServletRequest request, 
			 @PathVariable long id, @RequestBody EgamesMatch updateEgamesMatch) {
			
		EgamesMatch match =service.findOneEgames(updateEgamesMatch.getId());
			
		if(match != null){
			
			if (updateEgamesMatch.getWinnerTeam().equals(updateEgamesMatch.getHomeTeam())) {
				updateEgamesMatch.setWinHome(true);
				updateEgamesMatch.setWinVisiting(false);
			} else {
				updateEgamesMatch.setWinHome(false);
				updateEgamesMatch.setWinVisiting(true);
			}

			if (updateEgamesMatch.getFirstBloodTeam().equals(updateEgamesMatch.getHomeTeam())) {
				updateEgamesMatch.setFirstBloodHome(true);
				updateEgamesMatch.setFirstBloodVisiting(false);
			} else {
				updateEgamesMatch.setFirstBloodHome(false);
				updateEgamesMatch.setFirstBloodVisiting(true);
			}

			updateEgamesMatch.setFinished(true);
			callToCheckFinish();
			
			
			service.saveEgamesMatch(updateEgamesMatch);
			return new ResponseEntity<>(updateEgamesMatch, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	/* PUT SPORT MATCH FIND BY Id*/
	@PutMapping("/sports/{id}")
	public ResponseEntity<SportsMatch> updateSportMatchByID(HttpServletRequest request, 
			 @PathVariable long id, @RequestBody SportsMatch updateSportsMatch) {
			
		SportsMatch match = service.findOneSports(id);
			
		if(match != null){
			
			match.setHomePoints(updateSportsMatch.getHomePoints());
			match.setVisitingPoints(updateSportsMatch.getVisitingPoints());

			match.setFinished(true);
			callToCheckFinish();
			
			
			service.saveSportsMatch(match);
			return new ResponseEntity<>(match, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	/* POST A NEW SPORT MATCH*/
	@PostMapping("/sports")
	@ResponseStatus(HttpStatus.CREATED)
	public SportsMatch createSportMatch(@RequestBody SportsMatch sportsMatch) {

		service.saveSportsMatch(sportsMatch);

		return sportsMatch;
	}
	
	/* POST A NEW EGAMES MATCH*/
	@PostMapping("/egames")
	@ResponseStatus(HttpStatus.CREATED)
	public EgamesMatch createEgamesMatch(@RequestBody EgamesMatch match) {

		service.saveEgamesMatch(match);

		return match;
	}

	
	public void callToCheckFinish() {

		for (BetTicket ticket : betTicketService.findAll()) {
			ticket.checkFinishedTicket();
		}

	}

	

}
