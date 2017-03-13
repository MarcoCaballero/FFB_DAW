package com.ffbet.fase3.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

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
import com.ffbet.fase3.repositories.BetTicketRepository;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.Sports_match_repository;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

@Controller
public class AdminScoreController extends RedirectController {

	private String template = TemplatesPath.ADMIN_RESULTS.toString();
	private String redirect = "redirect:/admin-scores/";

	@Autowired
	private Sports_match_repository sports_match_repository;
	@Autowired
	private Egames_match_repository egames_match_repository;

	@Autowired
	UserAuthComponent userComp;
	@Autowired
	UserRepository userRepo;
	@Autowired
	BetTicketRepository betticketRepo;

	@GetMapping(value = { "/admin-scores", "/admin-scores/" })
	public String getScoreTemplate(HttpServletRequest request, Model model) {


		if (userComp.isLoggedUser()) {
			model.addAttribute("user", userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
			if (!userComp.getLoggedUser().isPhotoSelected()) {
				model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			} else {
				// Use image controller
			}
		} else {
			return "redirect:/logOut";
		}

	
		
		model.addAttribute("footballMatch",sports_match_repository.findByType("Fútbol",new PageRequest(0,100)));
		model.addAttribute("basketballMatch",sports_match_repository.findByType("Baloncesto",new PageRequest(0,100)));
		model.addAttribute("lolMatch",egames_match_repository.findByType("LOL",new PageRequest(0,100)));
		model.addAttribute("csgoMatch",egames_match_repository.findByType("CS-GO",new PageRequest(0,100)));
		
		String response = check_url(request, template);
		return response;

	}

	@PostMapping(value = { "/admin-scores/updateSports/{id}", "/admin-scores/updateSports/{id}/" })
	public String updateSportsScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id,
			@RequestParam String homePoints, @RequestParam String visitingPoints) {

		try {
			SportsMatch sportMatch = sports_match_repository.findOne(id);
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
			sports_match_repository.save(sportMatch);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// model.addAttribute("sportsMatch",sports_match_repository.findAll());
		return redirect;

	}

	@PostMapping(value = { "/admin-scores/updateEgames/{id}", "/admin-scores/updateEgames/{id}/" })
	public String updateEgamesScoreByID(HttpServletRequest request, Model model, @PathVariable("id") long id,
			@RequestParam String winningTeam, @RequestParam String firstBlood) {

		EgamesMatch egamesMatch = egames_match_repository.findOne(id);
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
			egames_match_repository.save(egamesMatch);
		} catch (Exception e) {

			System.out.println("s");
		}

		System.out.println(winningTeam);
		System.out.println(firstBlood);
		return redirect;

	}

	public void callToCheckFinish() {

		for (BetTicket ticket : betticketRepo.findAll()) {
			ticket.checkFinishedTicket();
		}

	}

}
