package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		model.addAttribute("footballMatch",sports_match_repository.findByType("Futbol"));
		model.addAttribute("basketballMatch",sports_match_repository.findByType("Baloncesto"));
		model.addAttribute("egamesMatch",egames_match_repository.findAll());
		
		String response = check_url(request, template);
		return response;

	}
	
	@RequestMapping(value = {"/admin-scores/update/{id}", "/admin-scores/update/{id}/"})
	public String updateScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id, 
			@RequestParam int homePoints, @RequestParam int visitingPoints) {
		SportsMatch sportMatch = sports_match_repository.findOne(id);
		sportMatch.setHomePoints(homePoints);
		sportMatch.setVisitingPoints(visitingPoints);
		sports_match_repository.save(sportMatch);
			
		model.addAttribute("sportsMatch",sports_match_repository.findAll());
		return redirect;
		
	}
	

}
