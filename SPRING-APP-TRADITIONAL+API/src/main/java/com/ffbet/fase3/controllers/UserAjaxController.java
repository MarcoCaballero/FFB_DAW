package com.ffbet.fase3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.services.MatchService;

@RestController
public class UserAjaxController {
	
	@Autowired
	private MatchService matchService;
	
	@RequestMapping(value = { "/user/footbalResults/"}, method= RequestMethod.GET)
	public Page<SportsMatch> getFootballMatch(Pageable page) {

		return matchService.findByTypeFinishedSports("Fútbol", page);
	}
	
	@RequestMapping(value = { "/user/basketballResults/"}, method= RequestMethod.GET)
	public Page<SportsMatch> getBasketballMatch(Pageable page) {

		return matchService.findByTypeFinishedSports("Baloncesto", page);
	}
	
	@RequestMapping(value = { "/user/lolResults/"}, method= RequestMethod.GET)
	public Page<EgamesMatch> getLolMatch(Pageable page) {

		return matchService.findByTypeFinishedEgames("LOL", page);
	}
	
	@RequestMapping(value = { "/user/csgoResults/"}, method= RequestMethod.GET)
	public Page<EgamesMatch> getCsgoMatch(Pageable page) {

		return matchService.findByTypeFinishedEgames("CS-GO", page);
	}
	

}
