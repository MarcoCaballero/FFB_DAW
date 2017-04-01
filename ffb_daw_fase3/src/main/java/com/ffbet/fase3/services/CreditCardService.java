package com.ffbet.fase3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.CreditCard;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepo;

	@Autowired
	UserService userService;
	
	public CreditCard getCard(String cardNumber){
		return creditCardRepo.findByCardNumber(cardNumber).get(0);
	}

	public boolean saveCreditCard(CreditCard credit, String amount, boolean error) {
		error = false;
		
		User user = userService.handleUserLoggedFromComponent();

		boolean exists = false;
		CreditCard creditcard = null;
		for (int i = 0; i < user.getCards().size(); i++) {
			if (user.getCards().get(i).getCardNumber().equals(credit.getCardNumber())) {
				exists = true;
				creditcard = user.getCards().get(i);
			}
		}

		if (exists) {
			if (creditcard != null) {
				if (creditcard.equalsData(credit.getType(), credit.getName(), credit.getCardNumber(),
						credit.getExpirationMonth(), credit.getExpirationYear(), credit.getSecurityCode())) {
					long amountSelected = Long.parseLong(amount);
					if (creditcard.sendMoney(amountSelected)) {
						user.addCreditfromCard(amountSelected);
					} else {
						// NOT CREDIT
						error = true;
					}
					CreditCard cd = creditCardRepo.findByCardNumber(credit.getCardNumber()).get(0);
					creditCardRepo.save(cd);
					userService.updateUser(user);
				} else {
					error = true;
				}

			}
		} else {

			long amountSelected = Long.parseLong(amount);

			creditcard = new CreditCard(credit.getType(), credit.getName(), credit.getCardNumber(),
					credit.getExpirationMonth(), credit.getExpirationYear(), credit.getSecurityCode());
			user.addCard(creditcard);
			if (creditcard.sendMoney(amountSelected)) {
				user.addCreditfromCard(amountSelected);
			} else {
				// NOT CREDIT
				error = true;
			}

			creditCardRepo.save(credit);
			userService.updateUser(user);
		}
		return error;
	}
	
	public void takeCredit(CreditCard credit, String amount){
		if(!credit.getCardNumber().equals("NO")){
			double amountD = Double.parseDouble(amount);
			User user = userService.handleUserLoggedFromComponent();
			for (CreditCard card : user.getCards()) {
				if (card.getCardNumber().equals(credit.getCardNumber())) {
					user.addCreditToCard(card.getId(), amountD);
//					creditCardRepo.save(credit);
					userService.updateUser(user);
				}
			}
		}
	}
}
