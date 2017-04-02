package com.ffbet.fase3.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.EgamesMatch;
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

	// provisional
	private boolean selectedOne = false;
	private boolean selectedTwo = false;
	private boolean selectedThree = false;
	private boolean selectedFour = false;
	private boolean selectedFive = false;

	/* BetMatch to ticket erasable zone */
	@PostMapping(value = { "/match?type=sports/{id}/{quota}", "/match?type=egames/{id}/{quota}" })
	public ResponseEntity<BetTicket> addSportMatchToLocalBet(@PathVariable long id, @PathVariable String quota,
			HttpServletRequest request) {
		Match match;
		if (request.getRequestURI().contains("sports")) {
			match = matchService.findOneSports(id);
		} else {
			match = matchService.findOneEgames(id);
		}

		if (match != null) {

			if (match.getClass().isInstance(SportsMatch.class)) {
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

	@DeleteMapping("/match/{id}")
	public ResponseEntity<BetTicket> removeMatchFromLocalBet(@PathVariable long id) {
		SportsMatch match = matchService.findOneSports(id);

		if (match != null) {
			ticket_erasable_SP = btService.removeMatchFromErasableTicket(ticket_erasable_SP, id);

			return new ResponseEntity<>(ticket_erasable_SP, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/* Quota setter zone */

	@PostMapping("/quota/{prize}")
	public ResponseEntity<BetTicket> setQuotaOnLocalBet(@PathVariable int prize) {
		ticket_erasable_SP = btService.setSelectedMultiplicator(selectedOne, selectedTwo, selectedThree, selectedFour,
				selectedFive, btService.switchMultiplicator(prize), ticket_erasable_SP);

		if (ticket_erasable_SP != null) {
			ticket_erasable_SP.setPotentialGain(ticket_erasable_SP.calculatePotentialGain(ticket_erasable_SP.getAmount()));
			return new ResponseEntity<>(ticket_erasable_SP, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/quota/{id}")
	public ResponseEntity<Double> getQuotaOnLocalBet(@PathVariable long id) {
		BetTicket bt = btService.findOne(id);
		if (bt != null) {
			return new ResponseEntity<>(bt.getAmount(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/* SEND BET ZONE */

	@PostMapping("?code={code}&?promoQuantity={promoQuantity}")
	public ResponseEntity<BetTicket> sendSportBet(HttpServletRequest request, Model model, @PathVariable String code,
			@PathVariable int promoQuantity) {

		if (ticket_erasable_SP != null) {
			if (btService.sendBet(ticket_erasable_SP, userService.handleUserLoggedFromComponent(), code,
					promoQuantity) == 0) {
				return new ResponseEntity<>(ticket_erasable_SP, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
