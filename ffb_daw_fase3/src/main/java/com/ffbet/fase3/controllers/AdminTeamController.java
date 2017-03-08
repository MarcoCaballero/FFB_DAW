package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.Sports_match_repository;

/**
 * Controller class {@link AdminTeamController} provides methods to map the URL's
 * that reference to the {@link Team} Entity. This controller also extends to an
 * Abstract class {@link RedirectController} that provides methods common to
 * several controllers
 * 
 * 
 * @see {@link Team}, {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class AdminTeamController extends RedirectController {

	String template =  TemplatesPath.ADMIN_TEAM.toString();
	String redirect = "redirect:/admin-teams/";
	@Autowired
	Sports_match_repository sport_match_repo;
	@Autowired
	Egames_match_repository egames_match_repo;
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
	@GetMapping(value = { "/admin-teams","/admin-teams/" })
	public String getTeamTemplate(HttpServletRequest request, Model model) {

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, template);
		return response;

	}

		
	

	/**
	 * Method {@linkplain addTeam()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the new team ADDED.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping(value = { "/admin-teams/new", "/admin-teams/new/" })
	public String addTeam() {
		
		return redirect;

	}

	/**
	 * Method {@linkplain updateTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the updated team [selected by id].
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@PutMapping("/admin-teams/update/{id}")
	public String updateTeamByID(Model model, @PathVariable("id") long updating_id) {
	
		return redirect;
	}

	/**
	 * Method {@linkplain deleteTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser without hte deleted team [ById].
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@DeleteMapping("/admin-teams/delete/{id}")
	public String deleteTeamByID(HttpServletRequest request, Model model, @PathVariable("id") long id) {
	
		return redirect;

	}

	
}
