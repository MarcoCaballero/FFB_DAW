package com.ffbet.fase3.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.Sports_match_repository;

@RestController
public class MatchController {
	
	@Autowired
	private Sports_match_repository sportsRepo;
	
	@Autowired
	private Egames_match_repository egamesRepo;
	
	@PostConstruct
	public void init(){
		Date date = null;
		Time time = null;
		
		SportsMatch sports = new SportsMatch();
		sports.setDate(date);
		sports.setTime(time);
		sports.setHomeTeam("Real Madrid");
		sports.setVisitingTeam("Atlético");
		sports.setQuotaHomeVictory(50);
		sports.setQuotaVisitingVictory(30);
		sports.setQuotaDraw(40);
		sports.setHomePoints(1);
		sports.setVisitingPoints(2);
		
		sportsRepo.save(sports);
		
		EgamesMatch egames = new EgamesMatch();
		egames.setDate(date);
		egames.setTime(time);
		egames.setHomeTeam("SKT T1");
		egames.setVisitingTeam("Origen");
		egames.setQuotaHomeVictory(50);
		egames.setQuotaVisitingVictory(30);
		egames.setQuotaHomeFirstBlood(22);
		egames.setQuotaVisitingVictory(15);
		
		egamesRepo.save(egames);
		
	}
	
	@RequestMapping("/sports/")
	public List<SportsMatch> showSports(){
		return sportsRepo.findAll();
	}
	
	@RequestMapping("/egames/")
	public List<EgamesMatch> showEgames(){
		return egamesRepo.findAll();
	}

}
