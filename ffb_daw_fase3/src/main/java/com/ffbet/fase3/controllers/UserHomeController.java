package com.ffbet.fase3.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffbet.fase3.domain.SportsMatch;
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

		model.addAttribute("footballMatchTable", sports_match_repository.findByTypeFinished("Fútbol",new PageRequest(0,10)));
		model.addAttribute("basketballMatchTable", sports_match_repository.findByTypeFinished("Baloncesto",new PageRequest(0,10)));
		model.addAttribute("lolMatchTable", egames_match_repository.findByTypeFinished("LOL",new PageRequest(0,10)));
		model.addAttribute("csgoMatchTable", egames_match_repository.findByTypeFinished("CS-GO",new PageRequest(0,10)));

		/*model.addAttribute("footballMatchTable", matchRepository.findByFinished("Fútbol"));
		model.addAttribute("basketballMatchTable", matchRepository.findByFinished("Baloncesto"));
		model.addAttribute("lolMatchTable", matchRepository.findByFinished("LOL"));
		model.addAttribute("csgoMatchTable", matchRepository.findByFinished("CS-GO"));
    */
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_HOME.toString());
		return response;

	}
	
	@GetMapping(value = { "/moreResults"})
	public List<SportsMatch> moreResults() {
		List<SportsMatch> scores = new ArrayList<SportsMatch>();
		List<SportsMatch> listaAux = sports_match_repository.findAll();
		for(int i=(pages*1); i<listaAux.size();i++){
			if(i==1)
				break;
			scores.add(listaAux.get(i));
		}
		return scores;

	}
	
	



}
