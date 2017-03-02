package com.ffbet.fase3.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.Egames_match;
import com.ffbet.fase3.domain.Sports_match;
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
		
		Sports_match sports = new Sports_match();
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
		
		Egames_match egames = new Egames_match();
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
	public List<Sports_match> showSports(){
		return sportsRepo.findAll();
	}
	
	@RequestMapping("/egames/")
	public List<Egames_match> showEgames(){
		return egamesRepo.findAll();
	}

}
