package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.repositories.EgamesTeamRepository;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	TeamRepository teamRepo;
	@Autowired
	SportTeamRepository sportsTeamRepo;
	@Autowired
	EgamesTeamRepository egamesTeamRepo;
	
	// findAll
	public List<Team> findAllTeams(){
		return teamRepo.findAll();
	}
	
	public List<SportTeam> findAllSportsTeams(){
		return sportsTeamRepo.findAll();
	}
	
	public List<EgamesTeam>findAllEgamesTeams(){
		return egamesTeamRepo.findAll();
	}
	
	// findOne
	public Team findOneTeam(long id){
		return teamRepo.findOne(id);
	}
	
	public SportTeam findOneSportTeam(long id){
		return sportsTeamRepo.findOne(id);
	}
	
	public EgamesTeam findOneEgamesTeam(long id){
		return egamesTeamRepo.findOne(id);
	}
	
	// save
	public void saveSportTeam(SportTeam sportTeam){
		sportsTeamRepo.save(sportTeam);
	}
	
	public void saveEgamesTeam(EgamesTeam egamesTeam){
		egamesTeamRepo.save(egamesTeam);
	}
	
	//delete
	public void delete(long id){
		teamRepo.delete(id);
	}
	
	//findByName
	public SportTeam findByNameSport(String name){
		return sportsTeamRepo.findByName(name);
	}
	
	public EgamesTeam findByNameEgames(String name){
		return egamesTeamRepo.findByName(name);
	}
	
	//findByType
	public List<SportTeam> findByTypeSports(String type){
		return sportsTeamRepo.findByType(type);
	}
	public List<EgamesTeam> findByTypeEgames(String type){
		return egamesTeamRepo.findByType(type);
	}

}
