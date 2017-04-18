package com.ffbet.fase3.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.Match;
import com.ffbet.fase3.domain.SportsMatch;
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
	@PatchMapping("/match/{id}/{type}/{quota}")
	public ResponseEntity<BetTicket> addSportMatchToLocalBet(@PathVariable long id, @PathVariable String quota,
			@PathVariable String type, HttpServletRequest request) {
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
	@DeleteMapping("/match/{id}/{type}") 
	public ResponseEntity<BetTicket> removeMatchFromLocalBet(@PathVariable long id, @PathVariable String type) {

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

	@PostMapping("/{amount}") 
	public ResponseEntity<BetTicket> sendSportBet(@PathVariable int amount) {
		
		String code = "";
		int promoQuantity = 0;

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
	@GetMapping("/{id}") 
	public ResponseEntity<BetTicket> validateBet(@PathVariable long id) {

		BetTicket bttocheck = btService.findOne(id);

		if (bttocheck != null) {
			// salida en json
			if (bttocheck.isFinished()){
				btService.validateBet(id);
			}
			
			return new ResponseEntity<>(btService.findOne(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BetTicket> removeBet(@PathVariable long id) {

		BetTicket bttocheck = btService.findOne(id);

		if (bttocheck != null) {

			btService.removeBetTicketFromUser(id);

			return new ResponseEntity<>(bttocheck, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
