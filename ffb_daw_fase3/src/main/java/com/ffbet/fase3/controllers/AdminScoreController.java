package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.UserService;

@Controller
public class AdminScoreController extends RedirectController {

	private String template = TemplatesPath.ADMIN_RESULTS.toString();
	private String redirect = "redirect:/admin-scores/";
	
	@Autowired
	private UserService userService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private BetTicketService betTicketService;

	@Autowired
	UserAuthComponent userComp;

	@GetMapping(value = { "/admin-scores", "/admin-scores/" })
	public String getScoreTemplate(HttpServletRequest request, Model model) {


		if (userComp.isLoggedUser()) {
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		} else {
			return "redirect:/logOut";
		}

		model.addAttribute("footballMatch", matchService.findByTypeSports("Fútbol", new PageRequest(0,100)));
		model.addAttribute("basketballMatch", matchService.findByTypeSports("Baloncesto",new PageRequest(0,100)));
		model.addAttribute("lolMatch", matchService.findByTypeEgames("LOL",new PageRequest(0,100)));
		model.addAttribute("csgoMatch", matchService.findByTypeEgames("CS-GO",new PageRequest(0,100)));
		
		String response = check_url(request, template);
		return response;

	}

	@PostMapping(value = { "/admin-scores/updateSports/{id}", "/admin-scores/updateSports/{id}/" })
	public String updateSportsScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id,
			@RequestParam String homePoints, @RequestParam String visitingPoints) {

		try {
			SportsMatch sportMatch = matchService.findOneSports(id);
			if (homePoints.equals("")) {
				homePoints = "0";
			}
			if (visitingPoints.equals("")) {
				visitingPoints = "0";
			}
			sportMatch.setHomePoints(Integer.parseInt(homePoints));

			sportMatch.setVisitingPoints(Integer.parseInt(visitingPoints));

			sportMatch.setFinished(true);
			callToCheckFinish();
			matchService.saveSportsMatch(sportMatch);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// model.addAttribute("sportsMatch",sports_match_repository.findAll());
		return redirect;

	}

	@PostMapping(value = { "/admin-scores/updateEgames/{id}", "/admin-scores/updateEgames/{id}/" })
	public String updateEgamesScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id,
			@RequestParam String winningTeam, @RequestParam String firstBlood) {

		EgamesMatch egamesMatch = matchService.findOneEgames(id);
		try {
			egamesMatch.setWinnerTeam(winningTeam);
			if (winningTeam.equals(egamesMatch.getHomeTeam())) {
				egamesMatch.setWinHome(true);
				egamesMatch.setWinVisiting(false);
			} else {
				egamesMatch.setWinHome(false);
				egamesMatch.setWinVisiting(true);
			}

			if (firstBlood.equals(egamesMatch.getHomeTeam())) {
				egamesMatch.setFirstBloodHome(true);
				egamesMatch.setFirstBloodVisiting(false);
			} else {
				egamesMatch.setFirstBloodHome(false);
				egamesMatch.setFirstBloodVisiting(true);
			}
			egamesMatch.setFirstBloodTeam(firstBlood);

			egamesMatch.setFinished(true);
			callToCheckFinish();
			matchService.saveEgamesMatch(egamesMatch);
		} catch (Exception e) {

			System.out.println("s");
		}

		System.out.println(winningTeam);
		System.out.println(firstBlood);
		return redirect;

	}

	public void callToCheckFinish() {

		for (BetTicket ticket : betTicketService.findAll()) {
			ticket.checkFinishedTicket();
		}

	}

}
