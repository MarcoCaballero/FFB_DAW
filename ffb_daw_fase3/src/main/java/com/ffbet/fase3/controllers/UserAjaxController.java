package com.ffbet.fase3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.Sports_match_repository;

@RestController
public class UserAjaxController {

	@Autowired
	private Sports_match_repository sport_match_repository;
	@Autowired
	private Egames_match_repository egames_match_repository;
	
	@RequestMapping(value = { "/user/footbalResults/"}, method= RequestMethod.GET)
	public Page<SportsMatch> getFootballMatch(Pageable page) {

		return sport_match_repository.findByTypeFinished("Fútbol",page);
	}
	
	@RequestMapping(value = { "/user/basketballResults/"}, method= RequestMethod.GET)
	public Page<SportsMatch> getBasketballMatch(Pageable page) {

		return sport_match_repository.findByTypeFinished("Baloncesto",page);
	}
	
	@RequestMapping(value = { "/user/lolResults/"}, method= RequestMethod.GET)
	public Page<EgamesMatch> getLolMatch(Pageable page) {

		return egames_match_repository.findByTypeFinished("LOL",page);
	}
	
	@RequestMapping(value = { "/user/csgoResults/"}, method= RequestMethod.GET)
	public Page<EgamesMatch> getCsgoMatch(Pageable page) {

		return egames_match_repository.findByTypeFinished("CS-GO",page);
	}
	

}
