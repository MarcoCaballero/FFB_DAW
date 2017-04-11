package com.ffbet.fase3.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.Match;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.UserService;

@RestController
@RequestMapping("/api/bet")
public class BetRestController {

	@Autowired
	private MatchService matchService;
	@Autowired
	private BetTicketService btService;
	@Autowired
	private UserService userService;

	BetTicket ticket_erasable_SP = null;
	BetTicket ticket_erasable_EG = null;

	/* BetMatch to ticket erasable zone */
	@PatchMapping("/match")
	public ResponseEntity<BetTicket> addSportMatchToLocalBet(@RequestParam long id, @RequestParam String quota,
			@RequestParam String type, HttpServletRequest request) {
		Match match;
		if (type.equals("sports")) {
			match = matchService.findOneSports(id);
		} else {
			match = matchService.findOneEgames(id);
		}

		if (match != null) {

			if (SportsMatch.class.isInstance(match)) {
				ticket_erasable_SP = btService.addSportMatchToErasableTicket(ticket_erasable_SP, id, quota);

				return new ResponseEntity<>(ticket_erasable_SP, HttpStatus.OK);
			} else {
				ticket_erasable_EG = btService.addEgamesMatchToErasableTicket(ticket_erasable_EG, id, quota);

				return new ResponseEntity<>(ticket_erasable_EG, HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/match") // .../match?id=id
	public ResponseEntity<BetTicket> removeMatchFromLocalBet(@RequestParam long id, @RequestParam String type) {

		Match match;
		if (type.equals("sports")) {
			match = matchService.findOneSports(id);
		} else {
			match = matchService.findOneEgames(id);
		}

		if (match != null) {

			if (SportsMatch.class.isInstance(match)) {
				ticket_erasable_SP = btService.removeMatchFromErasableTicket(ticket_erasable_SP, id);

				return new ResponseEntity<>(ticket_erasable_SP, HttpStatus.OK);
			} else {
				ticket_erasable_EG = btService.removeMatchFromErasableTicket(ticket_erasable_EG, id);

				return new ResponseEntity<>(ticket_erasable_EG, HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/* SEND BET ZONE */

	@PostMapping // ...?code=XXXXX&promoQuantity=XXXXX&amount=XXXX
	public ResponseEntity<BetTicket> sendSportBet(@RequestParam String code, @RequestParam int promoQuantity,
			@RequestParam int amount) {

		if (ticket_erasable_SP != null) {
			ticket_erasable_SP = btService.initializeAmountRest(amount, ticket_erasable_SP);
			if (btService.sendBet(ticket_erasable_SP, userService.handleUserLoggedFromComponent(), code,
					promoQuantity) == 0) {
				return new ResponseEntity<>(ticket_erasable_SP, HttpStatus.OK);
			}
		}

		if (ticket_erasable_EG != null) {
			ticket_erasable_EG = btService.initializeAmountRest(amount, ticket_erasable_EG);

			if (btService.sendBet(ticket_erasable_EG, userService.handleUserLoggedFromComponent(), code,
					promoQuantity) == 0) {
				return new ResponseEntity<>(ticket_erasable_EG, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/* VALIDATION BET ZONE */
	@GetMapping // ...?id=id
	public ResponseEntity<String> validateBet(@RequestParam long id) {

		BetTicket bttocheck = btService.findOne(id);

		if (bttocheck != null) {

			if (!bttocheck.isFinished())
				return new ResponseEntity<>("AÚN NO HA TERMINADO", HttpStatus.CONTINUE);

			if (btService.validateBet(id))
				return new ResponseEntity<>("HAS GANADO : " + bttocheck.getPotentialGain(), HttpStatus.OK);

			return new ResponseEntity<>("HAS PERDIDO : " + bttocheck.getAmount(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Ticket erróneo", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping // .....?id=id
	public ResponseEntity<User> removeBet(@RequestParam long id) {

		BetTicket bttocheck = btService.findOne(id);

		if (bttocheck != null) {

			btService.removeBetTicketFromUser(id);

			return new ResponseEntity<>(userService.handleUserLoggedFromComponent(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
