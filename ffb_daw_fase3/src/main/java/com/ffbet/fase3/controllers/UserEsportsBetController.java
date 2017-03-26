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

import com.ffbet.fase3.domain.BetESportMatch;
import com.ffbet.fase3.domain.BetSportMatch;
import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.PromoService;
import com.ffbet.fase3.services.UserService;

/**
 * @author Marco
 *
 */
@Controller
public class UserEsportsBetController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;
	@Autowired
	private UserService userService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private PromoService promoService;

	BetTicket ticket_erasable = null;

	private boolean showsUserMenu = false;
	private boolean showsPromoError = false;
	private boolean showsMoneyError = false;

	private boolean selectedOne = false;
	private boolean selectedTwo = false;
	private boolean selectedThree = false;
	private boolean selectedFour = false;
	private boolean selectedFive = false;

	private String redirect = "redirect:/user-EsportsBet/";

	@GetMapping(value = { "/user-EsportsBet", "/user-EsportsBet/" })
	public String getTemplate(HttpServletRequest request, Model model, HttpSession session,
			HttpServletResponse response) {

		showsUserMenu = false;
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		}
		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("ticketErasable", ticket_erasable);
		model.addAttribute("lolMatchTable", matchService.findByNotFinished("LOL"));
		model.addAttribute("csgoMatchTable", matchService.findByNotFinished("CS-GO"));

		model.addAttribute("selectedOne", selectedOne);
		model.addAttribute("selectedTwo", selectedTwo);
		model.addAttribute("selectedThree", selectedThree);
		model.addAttribute("selectedFour", selectedFour);
		model.addAttribute("selectedFive", selectedFive);

		model.addAttribute("showsMoneyError", showsMoneyError);
		model.addAttribute("showsPromoError", showsPromoError);

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String responseTemplate = check_url(request, TemplatesPath.USER_EGAMES_BET.toString());
		return responseTemplate;

	}

	@GetMapping(value = { "/user-EsportsBet/addMatch/{id}/{quota}", "/user-EsportsBet/addMatch/{id}/{quota}/" })
	public String addMatchToTicket(HttpServletRequest request, Model model, @PathVariable("id") String idPre,
			@PathVariable String quota) {
		boolean matchBetYet = false;

		try {
			long id = Long.parseLong(idPre);

			EgamesMatch match = matchService.findOneEgames(id);
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
				boolean isFBlocalSelected = false;
				boolean isFBVisitingSelected = false;
				switch (quota) {
				case "1":
					isLocalSelected = true;
					break;
				case "2":
					isVisitingSelected = true;
					break;
				case "FB1":
					isLocalSelected = true;
					break;
				case "FB2":
					isVisitingSelected = true;
					break;

				}
				BetESportMatch bm = new BetESportMatch(match, isLocalSelected, isVisitingSelected, isFBlocalSelected,
						isFBVisitingSelected);
				ticket_erasable.addEMatchTeam(bm);
				ticket_erasable.setPotentialGain(ticket_erasable.calculatePotentialGain(updatedMultiplicator()));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return redirect;

	}

	@GetMapping(value = { "/user-EsportsBet/refreshQuota/{prize}", "/user-EsportsBet/refreshQuota/{prize}" })
	public String refreshQuota(HttpServletRequest request, Model model, @PathVariable int prize) {

		switchMultiplicator(prize);
		if (ticket_erasable != null) {
			ticket_erasable.setPotentialGain(ticket_erasable.calculatePotentialGain(updatedMultiplicator()));
		}

		return redirect;

	}

	@PostMapping(value = { "/user-EsportsBet/sendBet", "/user-EsportsBet/sendBet/" })
	public String sendSportBet(HttpServletRequest request, Model model, @RequestParam("code") String code,
			@RequestParam("promoQuantity") int promoQuantity) {

		if (userComp.isLoggedUser()) {
			User updatedUser = userService.findByEmail(userComp.getLoggedUser().getEmail());
			ticket_erasable.setAmount(updatedMultiplicator());
			if (!code.equals("")) {
				showsPromoError = true;
				if (!promoService.findByPromotionCode(code).isEmpty()) {
					Promotion promoToapply = promoService.findByPromotionCode(code).get(0);
					showsPromoError = false;
					if (!updatedUser.addUsedPromo(promoToapply)) {
						ticket_erasable.applyPromo(promoToapply);
						showsPromoError = true;

						return redirect;
					}
				}

			}

			double amountToPay = ticket_erasable.getAmount();
			double promoQuantityDouble = Double.valueOf(promoQuantity);
			if (promoQuantityDouble > amountToPay) {
				promoQuantityDouble = amountToPay;
			}
			showsMoneyError = false;
			if (updatedUser.payFromPromotionCredit(promoQuantityDouble)) {
				amountToPay -= promoQuantityDouble;

			} else {
				showsMoneyError = true;
				return redirect;
			}

			if (!updatedUser.payFromCredit(amountToPay)) {
				// no credit error
				updatedUser.addPromotionCredit(promoQuantityDouble);
				showsMoneyError = true;
				return redirect;
			}
			// betTicketRepo.save(ticket_erasable);
			updatedUser.addBet(ticket_erasable);
			userService.save(updatedUser);
			ticket_erasable = null;

		} else {
			return "redirect:/login/";
		}

		return redirect;

	}

	@GetMapping(value = { "/user-EsportsBet/removeBetMatch/{id}", "/user-EsportsBet/removeBetMatch/{id}/" })
	public String sendSportBet(HttpServletRequest request, Model model, @PathVariable long id) {

		ticket_erasable.getBetEspMatchesList().remove(ticket_erasable.getBetEspMatchesList().get((int) id));

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
