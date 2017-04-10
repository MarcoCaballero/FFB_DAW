package com.ffbet.fase3.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.BetESportMatch;
import com.ffbet.fase3.domain.BetSportMatch;
import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.BetTicketRepository;
import com.ffbet.fase3.security.UserAuthComponent;

@Service
public class BetTicketService {

	@Autowired
	BetTicketRepository betTicketRepo;
	
	@Autowired
	private UserService userService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private PromoService promoService;

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

	/* local betticket methods */

	public BetTicket prepareErasableTicket(BetTicket bt) {
		if (bt == null) {
			bt = new BetTicket();
			bt.setAmount(1.0);
		}
		return bt;
	}

	public BetTicket addSportMatchToErasableTicket(BetTicket bt, long id, String quota) {

		SportsMatch match = matchService.findOneSports(id);
		bt = prepareErasableTicket(bt);

		if (!existsMatchYet(bt, id)) {
			BetSportMatch betMatch = new BetSportMatch(match, isLocalSelected(quota),
					!isLocalSelected(quota) && !isVisitingSelected(quota), isVisitingSelected(quota));
			bt.addMatchTeam(betMatch);
			bt.setPotentialGain(bt.calculatePotentialGain(bt.getAmount()));
			return bt;
		}

		return null;
	}

	public BetTicket addEgamesMatchToErasableTicket(BetTicket bt, long id, String quota) {

		EgamesMatch match = matchService.findOneEgames(id);
		bt = prepareErasableTicket(bt);

		if (!existsMatchYet(bt, id)) {
			BetESportMatch bm = new BetESportMatch(match, isLocalSelected(quota), isVisitingSelected(quota),
					isLocalFBSelected(quota), isVisitingFBSelected(quota));
			bt.addEMatchTeam(bm);
			bt.setPotentialGain(bt.calculatePotentialGain(bt.getAmount()));
			return bt;
		}

		return null;
	}

	public BetTicket removeMatchFromErasableTicket(BetTicket bt, long id) {
		bt.getBetMatches_list().remove(bt.getBetMatches_list().get((int) id));
		return bt;
	}

	/*
	 * Codigos de error: 0 = OKEY ; 1 = PROMO_NOT_FOUND ; 2 = PROMO_USED ; 3 =
	 * PAY_ERROR
	 */
	public int sendBet(BetTicket bt, User user, String code, double promoquantity) {

		System.out.println("code : " + code);

		if (!code.equals("")) {

			if (!verifyPromotionalCode(code))
				return 1;

			if (!verifyUserPromotionUsage(user, code))
				return 2;

			Promotion promoToapply = promoService.findByPromotionCode(code).get(0);
			bt.applyPromo(promoToapply);
		}

		if (!payServices(user, bt.getAmount(), promoquantity))
			return 3;

		user.addBet(bt);
		userService.updateUser(user);

		return 0;
	}

	public BetTicket initializeAmountRest(int amount, BetTicket bt) {
		double amountDouble = 1.0;
		try {
			amountDouble = (double) amount;
			bt.setAmount(amountDouble);
			bt.setPotentialGain(bt.calculatePotentialGain(bt.getAmount()));
			return bt;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean validateBet(long id) {
		User updateduser = userService.handleUserLoggedFromComponent();
		BetTicket ticketTocheck = findOne(id);

		if (!isBetCheckedYet(ticketTocheck, updateduser, id)) {
			if (ticketTocheck.checkTicket()) {
				if (ticketTocheck.checkFinishedTicket()) {
					updateduser.addCreditFromFFB(ticketTocheck.getPotentialGain());
					ticketTocheck.setWinned(true);
					ticketTocheck.setLosed(false);
					ticketTocheck.setUsed(true);
					// HA GANADO INGRESO DINERO
				} else {
					ticketTocheck.setWinned(false);
					ticketTocheck.setLosed(false);
				}

			} else {
				ticketTocheck.setWinned(false);
				ticketTocheck.setLosed(true);
				ticketTocheck.setUsed(true);
			}
		}

		userService.updateUser(updateduser);
		return ticketTocheck.isWinned();
	}

	public void removeBetTicketFromUser(long id) {
		User updateduser = userService.handleUserLoggedFromComponent();
		BetTicket ticketTocheck = findOne(id);
		updateduser.getBet_tickets().remove(ticketTocheck);
		userService.updateUser(updateduser);
	}

	/* Auxiliar bet methods */

	public boolean isBetCheckedYet(BetTicket btToCheck, User user, long id) {
		// TODO Auto-generated method stub

		for (BetTicket bt : user.getBet_tickets()) {
			if ((bt.getId() == id) && !bt.isUsed())
				return false;
		}

		return true;
	}

	public boolean isBetCheckedYet(long id) {
		// TODO Auto-generated method stub
		User updateduser = userService.handleUserLoggedFromComponent();
		for (BetTicket bt : updateduser.getBet_tickets()) {
			if ((bt.getId() == id) && !bt.isUsed())
				return true;
		}

		return false;
	}

	public boolean payServices(User user, Double amountToPay, Double promoQuantity) {

		Double amountReal = amountToPay;
		Double amountPromotionalPaid = promotionPayment(user, amountToPay, promoQuantity);
		amountReal -= amountPromotionalPaid;

		if (!user.payFromCredit(amountReal)) {
			user.addPromotionCredit(amountPromotionalPaid);
			userService.updateUser(user);
			return false;
		}

		return true;
	}

	public Double promotionPayment(User user, Double amountToPay, Double promoQuantity) {
		if (promoQuantity > amountToPay)
			promoQuantity = amountToPay;
		if (!user.payFromPromotionCredit(amountToPay))
			return 0.0;

		return amountToPay;
	}

	public boolean verifyUserPromotionUsage(User user, String code) {
		if (!promoService.findByPromotionCode(code).isEmpty()) {
			if (user.addUsedPromo(promoService.findByPromotionCode(code).get(0))) {
				userService.updateUser(user);
				return true;
			}
		}

		return false;
	}

	public boolean verifyPromotionalCode(String code) {

		if (promoService.findByPromotionCode(code).isEmpty())
			return false;

		return true;
	}

	public boolean existsMatchYet(BetTicket bt, long id) {
		for (BetSportMatch b : bt.getBetMatches_list()) {
			if (b.getMatch().getId() == id) {
				return true;
			}
		}

		for (BetESportMatch b : bt.getBetEspMatchesList()) {
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

	public boolean isLocalFBSelected(String quota) {
		if (quota.equals("FB1")) {
			return true;
		}
		return false;
	}

	public boolean isVisitingFBSelected(String quota) {
		if (quota.equals("FB2")) {
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

	public BetTicket setSelectedMultiplicator(boolean bOne, boolean bTwo, boolean bThree, boolean bFour, boolean bFive,
			Boolean[] selected, BetTicket bt) {
		int index = 0;

		for (int i = 0; i < selected.length; i++) {
			if (selected[i]) {
				index = i;
			}
		}
		bt.setAmount((double) getMoneyFromBoolean(index));
		return bt;
	}

	public int getMoneyFromBoolean(int i) {

		switch (i) {
		case 1:

			return 5;
		case 2:

			return 10;
		case 3:

			return 25;
		case 4:

			return 50;

		default:
			return 1;
		}

	}

}
