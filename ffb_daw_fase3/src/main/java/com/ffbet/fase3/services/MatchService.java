package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.repositories.Sports_match_repository;
import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.Match;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.MatchRepository;

@Service
public class MatchService {
	
	@Autowired
	MatchRepository matchRepo;
	@Autowired
	Sports_match_repository sportsRepo;
	@Autowired
	Egames_match_repository egamesRepo;
	
	// findAll
	public List<Match> findAllMatches(){
		return matchRepo.findAll();
	}
	
	public List<SportsMatch>findAllSports(){
		return sportsRepo.findAll();
	}
	
	public List<EgamesMatch> findAllEgames(){
		return egamesRepo.findAll();
	}
	
	// findOne
	public Match findOne(long id){
		return matchRepo.findOne(id);
	}
	
	public SportsMatch findOneSports(long id){
		return sportsRepo.findOne(id);
	}
	
	public EgamesMatch findOneEgames(long id){
		return egamesRepo.findOne(id);
	}
	
	// save
	public void saveSportsMatch(SportsMatch sportsMatch){
		sportsRepo.save(sportsMatch);
	}
	
	public void saveEgamesMatch(EgamesMatch egamesMatch){
		egamesRepo.save(egamesMatch);
	}
	
	//delete
	public void delete(long id){
		matchRepo.delete(id);
	}
	
	//findByTypeFinished
	public Page<SportsMatch> findByTypeFinishedSports(String type, Pageable page){
		return sportsRepo.findByTypeFinished(type, page);
	}
	
	public Page<EgamesMatch> findByTypeFinishedEgames(String type, Pageable page){
		return egamesRepo.findByTypeFinished(type, page);
	}
	
	// findByType Pageable
	public Page<SportsMatch> findByTypeSports(String type, Pageable page){
		return sportsRepo.findByType(type, page);
	}
	
	public Page<EgamesMatch> findByTypeEgames(String type, Pageable page){
		return egamesRepo.findByType(type, page);
	}
	
	// findByNotFinished
	public List<SportsMatch> findByNotFinished(String type){
		return matchRepo.findByNotFinished(type);
	}
	
	// findByFinished
	public List<SportsMatch> findByFinished(String type){
		return matchRepo.findByFinished(type);
	}
	
	// findByNotFinishedAndTeam
	public List<SportsMatch> findByNotFinishedAndTeam(String type, String nameTeam){
		return matchRepo.findByNotFinishedAndTeam(type, nameTeam);
	}
	
	// findByFinishedAndTeam
	public List<SportsMatch> findByFinishedAndTeam(String type, String nameTeam){
		return matchRepo.findByFinishedAndTeam(type, nameTeam);
	}

}
