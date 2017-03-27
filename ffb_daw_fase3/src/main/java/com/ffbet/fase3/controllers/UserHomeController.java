package com.ffbet.fase3.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.UserService;

/**
 * Controller class {@link UserHomeController} provides methods to map the URL's
 * that reference to the HOME. This controller also extends to an Abstract class
 * {@link RedirectController} that provides methods common to several
 * controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class UserHomeController extends RedirectController {

	@Autowired
	private UserService userService;
	@Autowired
	private MatchService matchService;

	@Autowired
	UserAuthComponent userComp;

	private boolean showsUserMenu = false;
	static boolean showButton = true;
	static int pages = 0;

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the HOME template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/user", "/user/", "/user/*" })
	public String getTemplate(HttpServletRequest request, Model model) {
		showsUserMenu = false;
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		}
		model.addAttribute("isUsermenuActive", showsUserMenu);

		model.addAttribute("footballMatchTable",
				matchService.findByTypeFinishedSports("Fútbol", new PageRequest(0, 10)));
		model.addAttribute("basketballMatchTable",
				matchService.findByTypeFinishedSports("Baloncesto", new PageRequest(0, 10)));
		model.addAttribute("lolMatchTable", matchService.findByTypeFinishedEgames("LOL", new PageRequest(0, 10)));
		model.addAttribute("csgoMatchTable", matchService.findByTypeFinishedEgames("CS-GO", new PageRequest(0, 10)));

		/*
		 * model.addAttribute("footballMatchTable",
		 * matchRepository.findByFinished("Fútbol"));
		 * model.addAttribute("basketballMatchTable",
		 * matchRepository.findByFinished("Baloncesto"));
		 * model.addAttribute("lolMatchTable",
		 * matchRepository.findByFinished("LOL"));
		 * model.addAttribute("csgoMatchTable",
		 * matchRepository.findByFinished("CS-GO"));
		 */
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_HOME.toString());
		return response;

	}

	@GetMapping(value = { "/moreResults" })
	public List<SportsMatch> moreResults() {
		List<SportsMatch> scores = new ArrayList<SportsMatch>();
		List<SportsMatch> listaAux = matchService.findAllSports();
		for (int i = (pages * 1); i < listaAux.size(); i++) {
			if (i == 1)
				break;
			scores.add(listaAux.get(i));
		}
		return scores;

	}

}
