package com.ffbet.fase3.controllers;

import java.sql.Date;
import java.sql.Time;

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
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.TeamService;
import com.ffbet.fase3.services.UserService;

/**
 * Controller class {@link AdminMatchesController} provides methods to map the
 * URL's that reference to the MATCHES, and their main controller functions.
 * This controller also extends to an Abstract class {@link RedirectController}
 * that provides methods common to several controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 * @author Pedro
 * @version 1.1
 */
@Controller
public class AdminMatchesController extends RedirectController {

	private String template = TemplatesPath.ADMIN_MATCH.toString();
	private String redirect = "redirect:/admin-matches/";
	
	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private MatchService matchService;
	
	
	@Autowired
	UserAuthComponent userComp;

	// Variables for football
	private boolean badDate = false;
	private boolean badTime = false;
	private boolean badQuota = false;
	private boolean equalTeams = false;
	private boolean sumQuotaOk = false;

	// Variables for basket
	private boolean badDateB = false;
	private boolean badTimeB = false;
	private boolean badQuotaB = false;
	private boolean equalTeamsB = false;
	private boolean sumQuotaOkB = false;

	// Variables for LOL
	private boolean badDateL = false;
	private boolean badTimeL = false;
	private boolean badQuotaL = false;
	private boolean equalTeamsL = false;
	private boolean sumQuota1OkL = false;
	private boolean sumQuota2OkL = false;

	// Variables for LOL
	private boolean badDateC = false;
	private boolean badTimeC = false;
	private boolean badQuotaC = false;
	private boolean equalTeamsC = false;
	private boolean sumQuota1OkC = false;
	private boolean sumQuota2OkC = false;

	/* ADMIN */

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-matches", "/admin-matches/" })
	public String getMatchTemplate(HttpServletRequest request, Model model) {
		
		if(userComp.isLoggedUser()){
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		}else{
			return "redirect:/logOut";
		}

		model.addAttribute("SportsTeams", teamService.findAllSportsTeams());
		model.addAttribute("EgamesTeams", teamService.findAllEgamesTeams());
		
		model.addAttribute("FootballTeams", teamService.findByTypeSports("Fútbol"));
		model.addAttribute("BasketTeams", teamService.findByTypeSports("Baloncesto"));
		
		model.addAttribute("LOLTeams", teamService.findByTypeEgames("LOL"));
		model.addAttribute("CSTeams", teamService.findByTypeEgames("CS-GO"));

		model.addAttribute("Matches", matchService.findAllMatches());
		model.addAttribute("Teams", teamService.findAllTeams());
		

		// Atributtes for football
		model.addAttribute("noDateFootball", badDate);
		model.addAttribute("noTimeFootball", badTime);
		model.addAttribute("noQuotaFootball", badQuota);
		model.addAttribute("noTeamsFootball", equalTeams);
		model.addAttribute("noSumQuotaFootball", sumQuotaOk);

		// Atributtes for basket
		model.addAttribute("noDateBasket", badDateB);
		model.addAttribute("noTimeBasket", badTimeB);
		model.addAttribute("noQuotaBasket", badQuotaB);
		model.addAttribute("noTeamsBasket", equalTeamsB);
		model.addAttribute("noSumQuotaBasket", sumQuotaOkB);

		// Atributtes for LOL
		model.addAttribute("noDateLOL", badDateL);
		model.addAttribute("noTimeLOL", badTimeL);
		model.addAttribute("noQuotaLOL", badQuotaL);
		model.addAttribute("noTeamsLOL", equalTeamsL);
		model.addAttribute("noSumQuota1LOL", sumQuota1OkL);
		model.addAttribute("noSumQuota2LOL", sumQuota2OkL);

		// Atributtes for CS
		model.addAttribute("noDateCS", badDateC);
		model.addAttribute("noTimeCS", badTimeC);
		model.addAttribute("noQuotaCS", badQuotaC);
		model.addAttribute("noTeamsCS", equalTeamsC);
		model.addAttribute("noSumQuota1CS", sumQuota1OkC);
		model.addAttribute("noSumQuota2CS", sumQuota2OkC);

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, template);
		return response;

	}

	/**
	 * Method {@linkplain addTeam()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the new match ADDED.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping(value = { "/admin-matches/newFootballMatch" })
	public String addFootballMatch(SportsMatch sportsMatch, @RequestParam("dateFootball") String date,
			@RequestParam("timeFootball") String time, @RequestParam("homeTeam") String homeTeam,
			@RequestParam("visitingTeam") String visitingTeam,
			@RequestParam("quotaHomeVictoryWebFootball") String quotaHomeVictory,
			@RequestParam("quotaDrawWebFootball") String quotaDraw,
			@RequestParam("quotaVisitingVictoryWebFootball") String quotaVisitingVictory) {

		try {
			sportsMatch.setDate(Date.valueOf(date));
			badDate = false;
		} catch (Exception e) {
			badDate = true;
		}

		try {
			sportsMatch.setTime(Time.valueOf(time.concat(":00")));
			badTime = false;
		} catch (Exception e) {
			badTime = true;
		}

		try {
			sportsMatch.setQuotaHomeVictory(Double.parseDouble(quotaHomeVictory));
			sportsMatch.setQuotaDraw(Double.parseDouble(quotaDraw));
			sportsMatch.setQuotaVisitingVictory(Double.parseDouble(quotaVisitingVictory));
			badQuota = false;

			double suma = Double.parseDouble(quotaHomeVictory) + Double.parseDouble(quotaVisitingVictory)
					+ Double.parseDouble(quotaDraw);

			if (suma == 110.0) {
				sumQuotaOk = false;
			} else {
				sumQuotaOk = true;
			}
		} catch (Exception e) {
			badQuota = true;
		}

		if (homeTeam.toString().equals(visitingTeam.toString())) {
			equalTeams = true;
		} else {
			equalTeams = false;
		}

		if (!badDate && !badTime && !badQuota && !equalTeams && !sumQuotaOk) {
			sportsMatch.setType("Fútbol");

			matchService.saveSportsMatch(sportsMatch);
		}

		return redirect;

	}

	@PostMapping(value = { "/admin-matches/newBasketMatch" })
	public String addBasketMatch(SportsMatch sportsMatch, @RequestParam("dateBasket") String date,
			@RequestParam("timeBasket") String time, @RequestParam("homeTeam") String homeTeam,
			@RequestParam("visitingTeam") String visitingTeam,
			@RequestParam("quotaHomeVictoryWebBasket") String quotaHomeVictory,
			@RequestParam("quotaVisitingVictoryWebBasket") String quotaVisitingVictory) {

		try {
			sportsMatch.setDate(Date.valueOf(date));
			badDateB = false;
		} catch (Exception e) {
			badDateB = true;
		}

		try {
			sportsMatch.setTime(Time.valueOf(time.concat(":00")));
			badTimeB = false;
		} catch (Exception e) {
			badTimeB = true;
		}

		try {
			sportsMatch.setQuotaHomeVictory(Double.parseDouble(quotaHomeVictory));
			sportsMatch.setQuotaVisitingVictory(Double.parseDouble(quotaVisitingVictory));
			badQuotaB = false;

			double suma = Double.parseDouble(quotaHomeVictory) + Double.parseDouble(quotaVisitingVictory);

			if (suma == 110.0) {
				sumQuotaOkB = false;
			} else {
				sumQuotaOkB = true;
			}
		} catch (Exception e) {
			badQuotaB = true;
		}

		if (homeTeam.toString().equals(visitingTeam.toString())) {
			equalTeamsB = true;
		} else {
			equalTeamsB = false;
		}

		if (!badDateB && !badTimeB && !badQuotaB && !equalTeamsB && !sumQuotaOkB) {
			sportsMatch.setType("Baloncesto");

			matchService.saveSportsMatch(sportsMatch);
		}

		return redirect;

	}

	@PostMapping("/admin-matches/newLOLMatch")
	public String addLOLMatch(EgamesMatch egamesMatch, @RequestParam("dateMatchEg") String date,
			@RequestParam("timeMatchEg") String time, @RequestParam("homeTeam") String homeTeam,
			@RequestParam("visitingTeam") String visitingTeam,
			@RequestParam("quotaHomeVictoryWebEg") String quotaHomeVictory,
			@RequestParam("quotaVisitingVictoryWebEg") String quotaVisitingVictory,
			@RequestParam("quotaHomeFirstBloodWebEg") String quotaHomeFirstBlood,
			@RequestParam("quotaVisitingFirstBloodWebEg") String quotaVisitingFirstBlood) {

		try {
			egamesMatch.setDate(Date.valueOf(date));
			badDateL = false;
		} catch (Exception e) {
			badDateL = true;
		}

		try {
			egamesMatch.setTime(Time.valueOf(time.concat(":00")));
			badTimeL = false;
		} catch (Exception e) {
			badTimeL = true;
		}

		try {
			egamesMatch.setQuotaHomeVictory(Double.parseDouble(quotaHomeVictory));
			egamesMatch.setQuotaVisitingVictory(Double.parseDouble(quotaVisitingVictory));
			egamesMatch.setQuotaHomeFirstBlood(Double.parseDouble(quotaHomeFirstBlood));
			egamesMatch.setQuotaVisitingFirstBlood(Double.parseDouble(quotaVisitingFirstBlood));
			badQuotaL = false;

			double suma1 = Double.parseDouble(quotaHomeVictory) + Double.parseDouble(quotaVisitingVictory);
			double suma2 = Double.parseDouble(quotaHomeFirstBlood) + Double.parseDouble(quotaVisitingFirstBlood);

			if (suma1 == 110.0 && suma2 == 110.0) {
				sumQuota1OkL = false;
			} else {
				sumQuota1OkL = true;
			}
			
			if (suma2 == 110.0) {
				sumQuota2OkL = false;
			} else {
				sumQuota2OkL = true;
			}

		} catch (Exception e) {
			badQuotaL = true;
		}

		if (homeTeam.toString().equals(visitingTeam.toString())) {
			equalTeamsL = true;
		} else {
			equalTeamsL = false;
		}

		if (!badDateL && !badTimeL && !badQuotaL && !equalTeamsL && !sumQuota1OkL && !sumQuota2OkL) {
			egamesMatch.setType("LOL");
			
			matchService.saveEgamesMatch(egamesMatch);
		}

		return redirect;

	}

	@PostMapping("/admin-matches/newCSMatch")
	public String addCSMatch(EgamesMatch egamesMatch, @RequestParam("dateMatchCs") String date,
			@RequestParam("timeMatchCs") String time, @RequestParam("homeTeam") String homeTeam,
			@RequestParam("visitingTeam") String visitingTeam,
			@RequestParam("quotaHomeVictoryWebCs") String quotaHomeVictory,
			@RequestParam("quotaVisitingVictoryWebCs") String quotaVisitingVictory,
			@RequestParam("quotaHomeFirstBloodWebCs") String quotaHomeFirstBlood,
			@RequestParam("quotaVisitingFirstBloodWebCs") String quotaVisitingFirstBlood) {

		try {
			egamesMatch.setDate(Date.valueOf(date));
			badDateC = false;
		} catch (Exception e) {
			badDateC = true;
		}

		try {
			egamesMatch.setTime(Time.valueOf(time.concat(":00")));
			badTimeC = false;
		} catch (Exception e) {
			badTimeC = true;
		}
		
		try {
			egamesMatch.setQuotaHomeVictory(Double.parseDouble(quotaHomeVictory));
			egamesMatch.setQuotaVisitingVictory(Double.parseDouble(quotaVisitingVictory));
			egamesMatch.setQuotaHomeFirstBlood(Double.parseDouble(quotaHomeFirstBlood));
			egamesMatch.setQuotaVisitingFirstBlood(Double.parseDouble(quotaVisitingFirstBlood));
			badQuotaC = false;

			double suma1 = Double.parseDouble(quotaHomeVictory) + Double.parseDouble(quotaVisitingVictory);
			double suma2 = Double.parseDouble(quotaHomeFirstBlood) + Double.parseDouble(quotaVisitingFirstBlood);

			if (suma1 == 110.0) {
				sumQuota1OkC = false;
			} else {
				sumQuota1OkC = true;
			}
			
			if (suma2 == 110.0) {
				sumQuota2OkC = false;
			} else {
				sumQuota2OkC = true;
			}

		} catch (Exception e) {
			badQuotaC = true;
			
		}

		if (homeTeam.toString().equals(visitingTeam.toString())) {
			equalTeamsC = true;
		} else {
			equalTeamsC = false;
		}
		
		System.out.println(homeTeam);
		
		if (!badDateC && !badTimeC && !badQuotaC && !equalTeamsC && !sumQuota1OkC && !sumQuota2OkC) {
			egamesMatch.setType("CS");
			
			matchService.saveEgamesMatch(egamesMatch);
		}

		return redirect;

	}

	/**
	 * Method {@linkplain deleteTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser without hte deleted match [ById].
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/admin-matches/delete/{id}")
	public String deleteMatchByID(@PathVariable long id) {

		matchService.delete(id);
		
		return redirect;

	}	
}
