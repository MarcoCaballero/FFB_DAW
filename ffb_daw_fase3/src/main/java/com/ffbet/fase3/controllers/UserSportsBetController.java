/**
 * 
 */
package com.ffbet.fase3.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ffbet.fase3.domain.BetSportMatch;
import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.BetSportMatchRepository;
import com.ffbet.fase3.repositories.BetTicketRepository;
import com.ffbet.fase3.repositories.MatchRepository;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.Sports_match_repository;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * @author Marco
 *
 */
@Controller
public class UserSportsBetController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;

	@Autowired
	UserRepository userRepo;

	@Autowired
	SportTeamRepository sportTeamRepository;

	@Autowired
	Sports_match_repository sportsMatchRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	BetSportMatchRepository betMatchRepo;

	@Autowired
	BetTicketRepository betTicketRepo;

	BetTicket ticket_erasable = null;

	private boolean showsUserMenu = false;
	
	private boolean selectedOne = false;
	private boolean selectedTwo = false;
	private boolean selectedThree = false;
	private boolean selectedFour = false;
	private boolean selectedFive = false;

	private String redirect = "redirect:/user-sportsBet/";

	@GetMapping(value = { "/user-sportsBet", "/user-sportsBet/" })
	public String getTemplate(HttpServletRequest request, Model model, HttpSession session,
			HttpServletResponse response) {

		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			User updatedUser = userRepo.findByEmail(userComp.getLoggedUser().getEmail());
			
			updatedUser.setPromotionCredit(10);
			model.addAttribute("user", updatedUser);

		} else {
			showsUserMenu = false;
		}

		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("ticketErasable", ticket_erasable);
		model.addAttribute("footballMatchTable", matchRepository.findByNotFinished("Fútbol"));
		model.addAttribute("basketballMatchTable", matchRepository.findByNotFinished("Baloncesto"));
		model.addAttribute("lolMatchTable", matchRepository.findByNotFinished("LOL"));
		model.addAttribute("csgoMatchTable", matchRepository.findByNotFinished("CS-GO"));

		model.addAttribute("selectedOne", selectedOne);
		model.addAttribute("selectedTwo", selectedTwo);
		model.addAttribute("selectedThree", selectedThree);
		model.addAttribute("selectedFour", selectedFour);
		model.addAttribute("selectedFive", selectedFive);

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String responseTemplate = check_url(request, TemplatesPath.USER_SPORT_BET.toString());
		return responseTemplate;

	}

	@GetMapping(value = { "/user-sportsBet/addMatch/{id}/{quota}", "/user-sportsBet/addMatch/{id}/{quota}/" })
	public String addMatchToTicket(HttpServletRequest request, Model model, @PathVariable("id") String idPre,
			@PathVariable String quota) {

		try {
			long id = Long.parseLong(idPre);
			SportsMatch match = sportsMatchRepository.findOne(id);
			if (ticket_erasable == null) {
				ticket_erasable = new BetTicket();
			}
			
			boolean isLocalSelected = false;
			boolean isVisitingSelected = false;
			switch (quota) {
			case "1":
				isLocalSelected = true;
				break;
			case "2":
				isVisitingSelected = true;
				break;
			
			}
			BetSportMatch betMatch = new BetSportMatch(match, isLocalSelected, !isLocalSelected && !isVisitingSelected,
					isVisitingSelected);
			ticket_erasable.addMatchTeam(betMatch);
			ticket_erasable.setPotential_gain(ticket_erasable.calculatePotentialGain(updatedMultiplicator()));
			System.out.println("GANAS :" + ticket_erasable.calculatePotentialGain(updatedMultiplicator()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return redirect;

	}

	@GetMapping(value = { "/user-sportsBet/refreshQuota/{prize}", "/user-sportsBet/refreshQuota/{prize}" })
	public String refreshQuota(HttpServletRequest request, Model model, @PathVariable int prize) {

		switchMultiplicator(prize);
		if (ticket_erasable != null) {
			ticket_erasable.setPotential_gain(ticket_erasable.calculatePotentialGain(updatedMultiplicator()));
		}

		return redirect;

	}
	
	
	
	@GetMapping(value = { "/user-sportsBet/sendBet", "/user-sportsBet/sendBet/{id}" })
	public String sendSportBet(HttpServletRequest request, Model model, @PathVariable String id) {

		if (userComp.isLoggedUser()) {
			User updatedUser = userRepo.findByEmail(userComp.getLoggedUser().getEmail());
			if(request.getRequestURI().endsWith("sendBet")){
								
				updatedUser.addBet(ticket_erasable);
				userRepo.save(updatedUser);
				ticket_erasable = null;
			}else{
				//handle promo; 
			}

		} else {
			return "redirect:/login/";
		}

		return redirect;

	}

	public void switchMultiplicator(int prize) {
		switch (prize) {
		case 1:
			selectedOne = true;
			selectedTwo = false;
			selectedThree = false;
			selectedFour = false;
			selectedFive = false;
			break;
		case 5:
			selectedOne = false;
			selectedTwo = true;
			selectedThree = false;
			selectedFour = false;
			selectedFive = false;
			break;
		case 10:
			selectedOne = false;
			selectedTwo = false;
			selectedThree = true;
			selectedFour = false;
			selectedFive = false;
			break;
		case 25:
			selectedOne = false;
			selectedTwo = false;
			selectedThree = false;
			selectedFour = true;
			selectedFive = false;
			break;
		case 50:
			selectedOne = false;
			selectedTwo = false;
			selectedThree = false;
			selectedFour = false;
			selectedFive = true;
			break;

		}

	}

	public int updatedMultiplicator() {
		
		if (selectedTwo)
			return 5;
		if (selectedThree)
			return 10;
		if (selectedFour)
			return 25;
		if (selectedFive)
			return 50;
		return 1;
	}

}
