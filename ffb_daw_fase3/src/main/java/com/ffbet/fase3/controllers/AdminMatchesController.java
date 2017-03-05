package com.ffbet.fase3.controllers;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.Sports_match_repository;

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
	private Sports_match_repository sportsMatchRepo;

	@Autowired
	private SportTeamRepository teamRepo;

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

		model.addAttribute("title", "Hello from ");

		model.addAttribute("Equipo1", teamRepo.findAll());
		model.addAttribute("Equipo2", teamRepo.findAll());
		
		model.addAttribute("Teams", teamRepo.findAll());
		
		model.addAttribute("Matches", sportsMatchRepo.findAll());

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, template);
		return response;

	}

	/**
	 * Method {@linkplain getTeams()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the match [selected by id]
	 * properties.
	 * 
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-matches/{id}", "/admin-matches/{id}/" })
	public String getMatchByID(HttpServletRequest request, Model model, @PathVariable("id") long id) {

		model.addAttribute("title", "Hello from " + String.valueOf(id));
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
	@PostMapping(value = { "/admin-matches/new" })
	public String addMatch(HttpServletRequest request, Model model, SportsMatch sportsMatch,
			@RequestParam ("homeTeam") String homeTeam, @RequestParam ("visitingTeam") String visitingTeam) {
		
		List<SportTeam> listaEquipos = teamRepo.findAll();
		
		//String homeTeam = (String) request.getAttribute("homeTeam");
		//String visitingTeam = (String) request.getAttribute("visitingTeam");
		
		sportsMatch.getTeams().add(teamRepo.findByName(homeTeam).get(0));
		sportsMatch.getTeams().add(teamRepo.findByName(visitingTeam).get(0));
		
		sportsMatchRepo.save(sportsMatch);

		// model.addAttribute("title", "Hello from new");
		return redirect;

	}

	/**
	 * Method {@linkplain updateTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the updated match [selected by id].
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@PutMapping("/admin-matches/update/{id}")
	public String updateMatchByID(HttpServletRequest request, Model model, @PathVariable("id") long updating_id) {

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, template);
		return response;

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
	@DeleteMapping("/admin-matches/delete/{id}")
	public String deleteMatchByID(HttpServletRequest request, Model model, @PathVariable("id") long id) {

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, template);
		return response;

	}
}
