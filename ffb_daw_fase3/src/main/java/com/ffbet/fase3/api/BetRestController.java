package com.ffbet.fase3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.MatchService;

@RestController
@RequestMapping("/api/bets")
public class BetRestController {

	@Autowired
	private MatchService matchService;
	@Autowired
	private BetTicketService btService;

	BetTicket ticket_erasable = null;

	// provisional
	private boolean selectedOne = false;
	private boolean selectedTwo = false;
	private boolean selectedThree = false;
	private boolean selectedFour = false;
	private boolean selectedFive = false;

	@PostMapping("/addMatch/{id}/{quota}")
	public ResponseEntity<BetTicket> addMatchToLocalBet(@PathVariable long id, @PathVariable String quota) {
		SportsMatch match = matchService.findOneSports(id);

		if (match != null) {
			ticket_erasable = btService.addMatchToErasableTicket(ticket_erasable, id, quota, updatedMultiplicator());
					
			return new ResponseEntity<>(ticket_erasable, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

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
