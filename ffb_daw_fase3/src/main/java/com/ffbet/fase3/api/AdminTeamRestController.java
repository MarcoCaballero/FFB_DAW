package com.ffbet.fase3.api;

import java.util.List;

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
	@GetMapping("/")
	public List<Team> getTeams(){
		return teamService.findAllTeams();
	}
	
	@GetMapping("/sports")
	public List<SportTeam> getSportTeams(){
		return teamService.findAllSportsTeams();
	}
	
	@GetMapping("/egames")
	public List<EgamesTeam> getEgamesTeams(){
		return teamService.findAllEgamesTeams();
	}
	
	@GetMapping("/sports/{id}")
	public ResponseEntity<Team> getSportTeamById(@PathVariable long id){
		Team team = teamService.findOneSportTeam(id);
		if(team != null){
			return new ResponseEntity<>(team, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//añadir equipos
	@PostMapping("/sports")
	@ResponseStatus(HttpStatus.CREATED)
	public SportTeam newSportTeam(@RequestBody SportTeam sportTeam){
		
		teamService.saveSportTeam(sportTeam);
		
		return sportTeam;
	}
	
	@PostMapping("/egames")
	@ResponseStatus(HttpStatus.CREATED)
	public EgamesTeam newEgamesTeam(@RequestBody EgamesTeam egamesTeam){
		
		teamService.saveEgamesTeam(egamesTeam);
		
		return egamesTeam;
	}
	
	// actuaizar equipos ¿por id o por nombre?
	@PutMapping("/sports/{id}")
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
	
	@PutMapping("/egames/{id}")
	public ResponseEntity<EgamesTeam> updateEgamesTeam(@PathVariable long id, @RequestBody EgamesTeam updatedTeam){
		
		EgamesTeam team = teamService.findOneEgamesTeam(id);
		
		if(team != null){
			updatedTeam.setId(team.getId());
			teamService.saveEgamesTeam(updatedTeam);
			
			return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	// borrar equipos
	@DeleteMapping("/{id}")
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
