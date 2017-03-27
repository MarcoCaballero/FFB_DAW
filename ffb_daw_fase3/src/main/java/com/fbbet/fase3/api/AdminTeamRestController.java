package com.fbbet.fase3.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.services.TeamService;

@RestController
@RequestMapping("/api/teams")
public class AdminTeamRestController {
	
	@Autowired
	private TeamService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<SportTeam> getTeams(){
		System.out.println("Paso por aquí");
		return service.findAllSportsTeams();
	}
	

}
