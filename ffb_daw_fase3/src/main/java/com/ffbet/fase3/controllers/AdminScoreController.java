package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.Sports_match_repository;

@Controller
public class AdminScoreController  extends RedirectController{
	
	private String template = TemplatesPath.ADMIN_RESULTS.toString();
	private String redirect = "redirect:/admin-scores/";
	
	@Autowired
	private Sports_match_repository sports_match_repository;
	@Autowired
	private Egames_match_repository egames_match_repository;

	@GetMapping(value = { "/admin-scores", "/admin-scores/" })
	public String getScoreTemplate(HttpServletRequest request, Model model) {

		
		model.addAttribute("footballMatch",sports_match_repository.findByType("Fútbol"));
		model.addAttribute("basketballMatch",sports_match_repository.findByType("Baloncesto"));
		model.addAttribute("lolMatch",egames_match_repository.findByType("LOL"));
		model.addAttribute("csgoMatch",egames_match_repository.findByType("CS-GO"));
		
		String response = check_url(request, template);
		return response;

	}
	
	
	@PostMapping(value = {"/admin-scores/updateSports/{id}", "/admin-scores/updateSports/{id}/"})
	public String updateSportsScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id, 
			@RequestParam String homePoints, @RequestParam String visitingPoints) {
		
		SportsMatch sportMatch = sports_match_repository.findOne(id);
		try {
			sportMatch.setHomePoints(Integer.parseInt(homePoints));
			
			sportMatch.setVisitingPoints(Integer.parseInt(visitingPoints));
			
			sportMatch.setFinished(true);
			
		} catch (Exception e) {
			System.out.println("Puntos "+homePoints);
		}
		sports_match_repository.save(sportMatch);
			
		//model.addAttribute("sportsMatch",sports_match_repository.findAll());
		return redirect;
		
	}
	
	@PostMapping(value = {"/admin-scores/updateEgames/{id}", "/admin-scores/updateEgames/{id}/"})
	public String updateEgamesScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id, 
			@RequestParam String winningTeam, @RequestParam String firstBlood ) {
		
		EgamesMatch egamesMatch = egames_match_repository.findOne(id);
		try {
			egamesMatch.setWinnerTeam(winningTeam);
			if(winningTeam.equals(egamesMatch.getHomeTeam())){
				egamesMatch.setWinHome(true);
				egamesMatch.setWinVisiting(false);
			}else{
				egamesMatch.setWinHome(false);
				egamesMatch.setWinVisiting(true);
			}
			
			if(firstBlood.equals(egamesMatch.getHomeTeam())){
				egamesMatch.setFirstBloodHome(true);
				egamesMatch.setFirstBloodVisiting(false);
			}else{
				egamesMatch.setFirstBloodHome(false);
				egamesMatch.setFirstBloodVisiting(true);
			}
			egamesMatch.setFirstBloodTeam(firstBlood);
			
			egamesMatch.setFinished(true);
			egames_match_repository.save(egamesMatch);
		} catch (Exception e) {
			
			System.out.println("s");
		}

		System.out.println(winningTeam);
		System.out.println(firstBlood);
		return redirect;
		
	}
	

}