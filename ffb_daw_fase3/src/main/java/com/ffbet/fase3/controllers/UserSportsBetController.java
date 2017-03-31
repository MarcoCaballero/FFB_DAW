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
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.PromoService;
import com.ffbet.fase3.services.UserService;

/**
 * @author Marco
 *
 */
@Controller
public class UserSportsBetController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;
	@Autowired
	private UserService userService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private PromoService promoService;
	@Autowired
	private BetTicketService btService;

	BetTicket ticket_erasable = null;

	private boolean showsUserMenu = false;
	private boolean showsPromoError = false;
	private boolean showsMoneyError = false;

	private boolean selectedOne = true;
	private boolean selectedTwo = false;
	private boolean selectedThree = false;
	private boolean selectedFour = false;
	private boolean selectedFive = false;

	private String redirect = "redirect:/user-sportsBet/";

	@GetMapping(value = { "/user-sportsBet", "/user-sportsBet/" })
	public String getTemplate(HttpServletRequest request, Model model, HttpSession session,
			HttpServletResponse response) {

		// Handle user if logged else returns null

		User updatedUser;
		if ((updatedUser = userService.handleUserLoggedFromComponent()) == null) {
			showsUserMenu = false;
		} else {
			showsUserMenu = true;
			model.addAttribute("user", updatedUser);
		}

		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("ticketErasable", ticket_erasable);
		model.addAttribute("footballMatchTable", matchService.findByNotFinished("Fútbol"));
		model.addAttribute("basketballMatchTable", matchService.findByNotFinished("Baloncesto"));

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

		try {
			long id = Long.parseLong(idPre);
			ticket_erasable = btService.addMatchToErasableTicket(ticket_erasable, id, quota);
			updateAmount((int) ticket_erasable.getAmount());
			ticket_erasable.calculatePotentialGain(ticket_erasable.getAmount());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return redirect;

	}

	@GetMapping(value = { "/user-sportsBet/refreshQuota/{prize}", "/user-sportsBet/refreshQuota/{prize}" })
	public String refreshQuota(HttpServletRequest request, Model model, @PathVariable int prize) {
		Boolean[] arrayBoolean = updateAmount(prize);
		ticket_erasable = btService.setSelectedMultiplicator(selectedOne, selectedTwo, selectedThree, selectedFour,
				selectedFive, arrayBoolean, ticket_erasable);
		if (ticket_erasable != null) {
			ticket_erasable.setPotentialGain(ticket_erasable.calculatePotentialGain(ticket_erasable.getAmount()));
		}

		return redirect;

	}

	@PostMapping(value = { "/user-sportsBet/sendBet", "/user-sportsBet/sendBet/" })
	public String sendSportBet(HttpServletRequest request, Model model, @RequestParam("code") String code,
			@RequestParam("promoQuantity") int promoQuantity) {

		User updatedUser = userService.handleUserLoggedFromComponent();
		if (updatedUser != null) {
			// ticket_erasable=btService.sendBet(ticket_erasable, updatedUser,
			// code, promoQuantity);

			switch (btService.sendBet(ticket_erasable, updatedUser, code, promoQuantity)) {
			case 1:
				showsPromoError = true;
				break;
			case 2:
				showsPromoError = true;
				break;

			case 3:
				showsMoneyError = true;
				break;

			default:
				showsPromoError = false;
				showsMoneyError = false;
				ticket_erasable = null;
				break;
			}

		} else {
			return "redirect:/login/";
		}

		return redirect;

	}

	@GetMapping(value = { "/user-sportsBet/removeBetMatch/{id}", "/user-sportsBet/removeBetMatch/{id}/" })
	public String sendSportBet(HttpServletRequest request, Model model, @PathVariable long id) {

		ticket_erasable.getBetMatches_list().remove(ticket_erasable.getBetMatches_list().get((int) id));

		return redirect;

	}

	public Boolean[] updateAmount(int prize) {
		Boolean[] arrayBoolean = btService.switchMultiplicator(prize);
		selectedOne = arrayBoolean[0];
		selectedTwo = arrayBoolean[1];
		selectedThree = arrayBoolean[2];
		selectedFour = arrayBoolean[3];
		selectedFive = arrayBoolean[4];
		ticket_erasable.setAmount((double) prize);
		return arrayBoolean;
	}

}
