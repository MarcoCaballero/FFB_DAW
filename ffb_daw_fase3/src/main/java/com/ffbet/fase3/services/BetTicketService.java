package com.ffbet.fase3.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.BetSportMatch;
import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.repositories.BetTicketRepository;

@Service
public class BetTicketService {

	@Autowired
	BetTicketRepository betTicketRepo;

	@Autowired
	private MatchService matchService;

	/* BET TICKET REPOSITORY METHODS */

	public List<BetTicket> findAll() {
		return betTicketRepo.findAll();
	}

	public BetTicket findOne(long id) {
		return betTicketRepo.findOne(id);
	}

	public List<BetTicket> findByFinished() {
		return betTicketRepo.findByFinished();
	}

	/* LOCAL BETTICKET METHODS */

	public BetTicket prepareErasableTicket(BetTicket bt) {
		if (bt == null) {
			bt = new BetTicket();
		}
		return bt;
	}

	public BetTicket addMatchToErasableTicket(BetTicket bt, long id, String quota, int multiplicator) {

		SportsMatch match = matchService.findOneSports(id);
		bt=prepareErasableTicket(bt);

		if (!existsMatchYet(bt, id)) {
			BetSportMatch betMatch = new BetSportMatch(match, isLocalSelected(quota),
					!isLocalSelected(quota) && !isVisitingSelected(quota), isVisitingSelected(quota));
			bt.addMatchTeam(betMatch);
			bt.setPotentialGain(bt.calculatePotentialGain(multiplicator));
			return bt;
		}

		return null;
	}

	public boolean existsMatchYet(BetTicket bt, long id) {
		for (BetSportMatch b : bt.getBetMatches_list()) {
			if (b.getMatch().getId() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean isLocalSelected(String quota) {
		if (quota.equals("1")) {
			return true;
		}
		return false;
	}

	public boolean isVisitingSelected(String quota) {
		if (quota.equals("2")) {
			return true;
		}
		return false;
	}

	public Boolean[] switchMultiplicator(int prize) {
		Boolean[] selected = new Boolean[5];
		Arrays.fill(selected, Boolean.FALSE);
		switch (prize) {
		case 1:
			selected[0] = true;
			selected[1] = false;
			selected[2] = false;
			selected[3] = false;
			selected[4] = false;
			break;
		case 5:
			selected[0] = false;
			selected[1] = true;
			selected[2] = false;
			selected[3] = false;
			selected[4] = false;
			break;
		case 10:
			selected[0] = false;
			selected[1] = false;
			selected[2] = true;
			selected[3] = false;
			selected[4] = false;
			break;
		case 25:
			selected[0] = false;
			selected[1] = false;
			selected[2] = false;
			selected[3] = true;
			selected[4] = false;
			break;
		case 50:
			selected[0] = false;
			selected[1] = false;
			selected[2] = false;
			selected[3] = false;
			selected[4] = true;
			break;

		}
		return selected;
	}

	public void setSelectedMultiplicator(boolean bOne, boolean bTwo, boolean bThree, boolean bFour, boolean bFive,
			Boolean[] selected) {
		bOne = selected[0];
		bTwo = selected[1];
		bThree = selected[2];
		bFour = selected[3];
		bFive = selected[4];

	}

}
