package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.PromoService;
import com.ffbet.fase3.services.TeamService;
import com.ffbet.fase3.services.UserService;

/**
 * Controller class {@link AdminHomeController} provides methods to map the
 * URL's that reference to the HOME, LOGIN, REGISTER, and other main functions.
 * This controller also extends to an Abstract class {@link RedirectController}
 * that provides methods common to several controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class AdminHomeController extends RedirectController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private PromoService promoService;
	@Autowired
	private BetTicketService betTicketService;
	
	@Autowired
	UserAuthComponent userComp;
	

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the HOME template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-home", "/admin-home/", "/admin", "/admin/" })
	public String getTemplate(HttpServletRequest request, Model model) {
		
		if(userComp.isLoggedUser()){
			
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
			if(!userComp.getLoggedUser().isPhotoSelected()){
				model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			}else{
				//Use image controller
			}
		}else{
			return "redirect:/logOut";
		}
				
		int totalMoney = 0;
		for (int i = 0; i < betTicketService.findAll().size(); i++) {
			BetTicket ticket = betTicketService.findAll().get(i);
			totalMoney += ticket.getAmount();
		}

		
		model.addAttribute("Users", userService.findAll());
		model.addAttribute("totalUSer", userService.findAll().size());
		model.addAttribute("totalTeams", teamService.findAllSportsTeams().size() + teamService.findAllEgamesTeams().size());
		model.addAttribute("totalBets", betTicketService.findAll().size());
		model.addAttribute("totalMoney", totalMoney);
		model.addAttribute("totalPromotions", promoService.findAll().size());
		model.addAttribute("totalMatches", matchService.findAllSports().size() + matchService.findAllEgames().size());

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.ADMIN_HOME.toString());
		return response;

	}

	@GetMapping("/admin-home/delete/{id}")
	public String removeUser(@PathVariable Long id) {

		userService.delete(id);
		return "redirect:/admin/";
	}

	@GetMapping("/admin-home/downgrade/{id}")
	public String downgradeUser(@PathVariable Long id) {

		User user = userService.findOne(id);

		if (user.getRoles().size() > 1) {
			user.setRoles("ROLE_USER");
		}

		userService.save(user);
		
		return "redirect:/admin/";
	}

	@GetMapping("/admin-home/upgrade/{id}")
	public String upgradeUser(@PathVariable Long id) {

		User user = userService.findOne(id);
		if (user.getRoles().size() < 2) {
			user.addRole("ROLE_ADMIN");
		}
	

		userService.save(user);

		return "redirect:/admin/";
	}

}
