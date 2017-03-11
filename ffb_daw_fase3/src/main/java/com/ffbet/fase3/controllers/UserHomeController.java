package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.MatchRepository;
import com.ffbet.fase3.repositories.Sports_match_repository;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * Controller class {@link UserHomeController} provides methods to map the URL's
 * that reference to the HOME. This
 * controller also extends to an Abstract class {@link RedirectController} that
 * provides methods common to several controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class UserHomeController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;
	@Autowired
	UserRepository userRepo;
	private boolean showsUserMenu = false;
	@Autowired
	Sports_match_repository sports_match_repository;
	@Autowired
	Egames_match_repository egames_match_repository;
	@Autowired
	MatchRepository matchRepository;

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
		
		if(userComp.isLoggedUser()){
			showsUserMenu = true;
			model.addAttribute("user",  userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
			if(!userComp.getLoggedUser().isPhotoSelected()){
				//model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			}else{
				//Use image controller
			}
		}else{
			showsUserMenu = false;
		}
		
		
		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("footballMatchTable", matchRepository.findByFinished("Fútbol"));
		model.addAttribute("basketballMatchTable", matchRepository.findByFinished("Baloncesto"));
		model.addAttribute("lolMatchTable", matchRepository.findByFinished("LOL"));
		model.addAttribute("csgoMatchTable", matchRepository.findByFinished("CS-GO"));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_HOME.toString());
		return response;

	}

}
