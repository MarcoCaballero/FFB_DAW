package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ffbet.fase3.domain.Team;

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
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/equipos");
		return response;

	}

		
	/**
	 * Method {@linkplain getTeams()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the team [selected by id]
	 * properties.
	 * 
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-teams/{id}", "/admin-teams/{id}/" })
	public String getTeamByID(HttpServletRequest request, Model model, @PathVariable("id") long id) {
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/equipos");
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
	public String addTeam(HttpServletRequest request, Model model) {
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/equipos");
		return response;

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
	public String updateTeamByID(HttpServletRequest request, Model model, @PathVariable("id") long updating_id) {
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/equipos");
		return response;

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
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/equipos");
		return response;

	}

	
}
