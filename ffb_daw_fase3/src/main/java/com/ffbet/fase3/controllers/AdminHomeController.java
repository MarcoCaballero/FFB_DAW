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
import com.ffbet.fase3.repositories.BetTicketRepository;
import com.ffbet.fase3.repositories.EgamesTeamRepository;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.PromotionRepository;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.Sports_match_repository;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

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
	UserRepository userRepo;
	@Autowired
	EgamesTeamRepository egamesTeamRepo;
	@Autowired
	SportTeamRepository sportTeamRepo;
	@Autowired
	PromotionRepository promoRepo;
	@Autowired
	BetTicketRepository betRepo;
	@Autowired
	Sports_match_repository sportMatchRepo;
	@Autowired
	Egames_match_repository egamesMatchRepo;
	@Autowired
	UserAuthComponent usercomponent;

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
		int totalMoney = 0;
		String activeUser = "NO REGISTRADO";
		for (int i = 0; i < betRepo.findAll().size(); i++) {
			BetTicket ticket = betRepo.findAll().get(i);
			totalMoney += ticket.getAmount();
		}

		if (usercomponent.isLoggedUser()) {
			User userlogged = usercomponent.getLoggedUser();
			activeUser = userlogged.getName();
		} else {
			return "redirect:/logOut";
		}

		model.addAttribute("UsuarioActivo", activeUser);
		model.addAttribute("Users", userRepo.findAll());
		model.addAttribute("totalUSer", userRepo.findAll().size());
		model.addAttribute("totalTeams", sportTeamRepo.findAll().size() + egamesTeamRepo.findAll().size());
		model.addAttribute("totalBets", betRepo.findAll().size());
		model.addAttribute("totalMoney", totalMoney);
		model.addAttribute("totalPromotions", promoRepo.findAll().size());
		model.addAttribute("totalMatches", sportMatchRepo.findAll().size() + egamesMatchRepo.findAll().size());

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.ADMIN_HOME.toString());
		return response;

	}

	@GetMapping("/admin-home/delete/{id}")
	public String removeUser(@PathVariable Long id) {

		userRepo.delete(id);
		return "redirect:/admin/";
	}

	@GetMapping("/admin-home/downgrade/{id}")
	public String downgradeUser(@PathVariable Long id) {
		
		User user = userRepo.findOne(id);
		
		if (user.getRoles().size() > 1) {
			user.setRoles("ROLE_USER");
		}
		
		userRepo.save(user);
		System.out.println("DOWNGRADE " + userRepo.findOne(id).getRoles().size());
		return "redirect:/admin/";
	}

	@GetMapping("/admin-home/upgrade/{id}")
	public String upgradeUser(@PathVariable Long id) {

		User user = userRepo.findOne(id);
		if (user.getRoles().size() < 2) {
			user.addRole("ROLE_ADMIN");
		}
		System.out.println("UPGRADE " + userRepo.findOne(id).getRoles().size());
		for (int i = 0; i < user.getRoles().size(); i++) {
			System.out.println("UPGRADE " + i + "ROLE : " + user.getRoles().get(i));
			
		}
		
		userRepo.save(user);
		
		return "redirect:/admin/";
	}

}
