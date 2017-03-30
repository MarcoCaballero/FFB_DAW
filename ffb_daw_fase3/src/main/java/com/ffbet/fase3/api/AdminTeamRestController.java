package com.ffbet.fase3.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.services.TeamService;

@RestController
@RequestMapping("/api/teams")
public class AdminTeamRestController {
	
	@Autowired
	private TeamService teamService;
	
	// mostrar equipos
	@RequestMapping(value = "/getTeams", method = RequestMethod.GET)
	public List<Team> getTeams(){
		return teamService.findAllTeams();
	}
	
	@RequestMapping(value = "/getSportTeams", method = RequestMethod.GET)
	public List<SportTeam> getSportTeams(){
		return teamService.findAllSportsTeams();
	}
	
	@RequestMapping(value = "/getEgamesTeams", method = RequestMethod.GET)
	public List<EgamesTeam> getEgamesTeams(){
		return teamService.findAllEgamesTeams();
	}
	
	//añadir equipos
	@PostMapping("/newSportTeam")
	@ResponseStatus(HttpStatus.CREATED)
	public SportTeam newSportTeam(@RequestBody SportTeam sportTeam){
		
		teamService.saveSportTeam(sportTeam);
		
		return sportTeam;
	}
	
	@PostMapping("/newEgamesTeam")
	@ResponseStatus(HttpStatus.CREATED)
	public EgamesTeam newEgamesTeam(@RequestBody EgamesTeam egamesTeam){
		
		teamService.saveEgamesTeam(egamesTeam);
		
		return egamesTeam;
	}
	
	// actuaizar equipos ¿por id o por nombre?
	@PutMapping("/updateSportTeam/{id}")
	public ResponseEntity<SportTeam> updateSportTeam(@PathVariable long id, @RequestBody SportTeam updatedTeam){
		
		SportTeam team = teamService.findOneSportTeam(id);
		
		if(team != null){
			updatedTeam.setId(team.getId());
			teamService.saveSportTeam(updatedTeam);
			
			return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/updateEgamesTeam/{name}")
	public ResponseEntity<EgamesTeam> updateEgamesTeam(@PathVariable String name, @RequestBody EgamesTeam updatedTeam){
		
		EgamesTeam team = teamService.findByNameEgames(name);
		
		if(team != null){
			updatedTeam.setId(team.getId());
			teamService.saveEgamesTeam(updatedTeam);
			
			return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	// borrar equipos
	@DeleteMapping("/deleteTeam/{id}")
	public ResponseEntity<Team> deleteTeam(@PathVariable long id){
		
		Team team = teamService.findOneTeam(id);
		
		if (team != null) {
			teamService.delete(id);
			return new ResponseEntity<>(team, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
