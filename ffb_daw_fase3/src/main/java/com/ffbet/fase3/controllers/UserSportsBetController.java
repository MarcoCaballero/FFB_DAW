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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ffbet.fase3.domain.BetSportMatch;
import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.BetSportMatchRepository;
import com.ffbet.fase3.repositories.BetTicketRepository;
import com.ffbet.fase3.repositories.MatchRepository;
import com.ffbet.fase3.repositories.PromotionRepository;
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

	@Autowired
	PromotionRepository promotionrepo;

	BetTicket ticket_erasable = null;

	private boolean showsUserMenu = false;
	private boolean showsPromoError = false;
	private boolean showsMoneyError = false;

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

			updatedUser.setPromotionCredit(20);
			userRepo.save(updatedUser);
			model.addAttribute("user", updatedUser);

		} else {
			showsUserMenu = false;
		}

		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("ticketErasable", ticket_erasable);
		model.addAttribute("footballMatchTable", matchRepository.findByNotFinished("Fútbol"));
		model.addAttribute("basketballMatchTable", matchRepository.findByNotFinished("Baloncesto"));

		model.addAttribute("selectedOne", selectedOne);
		model.addAttribute("selectedTwo", selectedTwo);
		model.addAttribute("selectedThree", selectedThree);
		model.addAttribute("selectedFour", selectedFour);
		model.addAttribute("selectedFive", selectedFive);

		model.addAttribute("showsMoneyError", showsMoneyError);
		model.addAttribute("showsPromoError", showsPromoError);

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String responseTemplate = check_url(request, TemplatesPath.USER_SPORT_BET.toString());
		return responseTemplate;

	}

	@GetMapping(value = { "/user-sportsBet/addMatch/{id}/{quota}", "/user-sportsBet/addMatch/{id}/{quota}/" })
	public String addMatchToTicket(HttpServletRequest request, Model model, @PathVariable("id") String idPre,
			@PathVariable String quota) {
		boolean matchBetYet = false;

		try {
			long id = Long.parseLong(idPre);

			SportsMatch match = sportsMatchRepository.findOne(id);
			if (ticket_erasable == null) {
				ticket_erasable = new BetTicket();
			}
			for (BetSportMatch b : ticket_erasable.getBetMatches_list()) {
				if (b.getMatch().getId() == id) {
					matchBetYet = true;
				}
			}
			if (!matchBetYet) {
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
				BetSportMatch betMatch = new BetSportMatch(match, isLocalSelected,
						!isLocalSelected && !isVisitingSelected, isVisitingSelected);
				ticket_erasable.addMatchTeam(betMatch);
				ticket_erasable.setPotential_gain(ticket_erasable.calculatePotentialGain(updatedMultiplicator()));

			}

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

	@PostMapping(value = { "/user-sportsBet/sendBet", "/user-sportsBet/sendBet/" })
	public String sendSportBet(HttpServletRequest request, Model model, @RequestParam("code") String code,
			@RequestParam("promoQuantity") int promoQuantity) {

		if (userComp.isLoggedUser()) {
			User updatedUser = userRepo.findByEmail(userComp.getLoggedUser().getEmail());
			ticket_erasable.setAmount(updatedMultiplicator());
			if (!code.equals("")) {
				showsPromoError = true;
				if (!promotionrepo.findByPromotionCode(code).isEmpty()) {
					Promotion promoToapply = promotionrepo.findByPromotionCode(code).get(0);
					showsPromoError = false;

					if (!ticket_erasable.applyPromo(promoToapply)) {
						showsPromoError = true;
					}
				}

			}

			double amountToPay = ticket_erasable.getAmount();
			showsMoneyError = false;
			if (updatedUser.payFromPromotionCredit(promoQuantity)) {
				amountToPay -= promoQuantity;

			} else {
				showsMoneyError = true;
				return redirect;
			}

			if (!updatedUser.payFromCredit(amountToPay)) {
				// no credit error
				updatedUser.addPromotionCredit(promoQuantity);
				showsMoneyError = true;
				return redirect;
			}
			// betTicketRepo.save(ticket_erasable);
			updatedUser.addBet(ticket_erasable);
			userRepo.save(updatedUser);
			ticket_erasable = null;

		} else {
			return "redirect:/login/";
		}

		return redirect;

	}

	@GetMapping(value = { "/user-sportsBet/removeBetMatch/{id}", "/user-sportsBet/removeBetMatch/{id}/" })
	public String sendSportBet(HttpServletRequest request, Model model, @PathVariable long id) {

		ticket_erasable.getBetMatches_list().remove(ticket_erasable.getBetMatches_list().get((int) id));
		System.out.println("REMOVED" + id);

		for (BetSportMatch b : ticket_erasable.getBetMatches_list()) {
			System.out.println("Partido : " + b.getId());
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
