package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.CreditCard;
import com.ffbet.fase3.repositories.CreditCardRepository;


@Service
public class CreditCardService {
	
	@Autowired
	CreditCardRepository creditCardRepo;

	// findAll
	public List<CreditCard> findAllCreditCards(){
		return creditCardRepo.findAll();
	}
	// findById
	public CreditCard findOneCreditCard(long id){
		return creditCardRepo.findOne(id);
	}
	//save
	public void saveCreditCard(CreditCard creditCard){
		creditCardRepo.save(creditCard);
	}
	//delete
	public void deleteCreditCard(long id){
		creditCardRepo.delete(id);
	}
}
