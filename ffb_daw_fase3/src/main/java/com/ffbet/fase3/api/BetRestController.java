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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.BetTicket;
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

	BetTicket ticket_erasable = null;

	// provisional
	private boolean selectedOne = false;
	private boolean selectedTwo = false;
	private boolean selectedThree = false;
	private boolean selectedFour = false;
	private boolean selectedFive = false;

	/* BetMatch to ticket erasable zone */
	@PostMapping("/match/{id}/{quota}")
	public ResponseEntity<BetTicket> addMatchToLocalBet(@PathVariable long id, @PathVariable String quota) {
		SportsMatch match = matchService.findOneSports(id);

		if (match != null) {
			ticket_erasable = btService.addMatchToErasableTicket(ticket_erasable, id, quota);

			return new ResponseEntity<>(ticket_erasable, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/match/{id}")
	public ResponseEntity<BetTicket> removeMatchFromLocalBet(@PathVariable long id) {
		SportsMatch match = matchService.findOneSports(id);

		if (match != null) {
			ticket_erasable = btService.removeMatchFromErasableTicket(ticket_erasable, id);

			return new ResponseEntity<>(ticket_erasable, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/* Quota setter zone */

	@PostMapping("/quota/{prize}")
	public ResponseEntity<BetTicket> setQuotaOnLocalBet(@PathVariable int prize) {
		ticket_erasable = btService.setSelectedMultiplicator(selectedOne, selectedTwo, selectedThree, selectedFour,
				selectedFive, btService.switchMultiplicator(prize), ticket_erasable);

		if (ticket_erasable != null) {
			ticket_erasable.setPotentialGain(ticket_erasable.calculatePotentialGain(ticket_erasable.getAmount()));

			return new ResponseEntity<>(ticket_erasable, HttpStatus.OK);
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
		
		if (ticket_erasable != null) {
			if (btService.sendBet(ticket_erasable, userService.handleUserLoggedFromComponent(), code,
					promoQuantity) == 0) {
				return new ResponseEntity<>(ticket_erasable, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
