package com.ffbet.fase3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.repositories.CreditCardRepository;

@Service
public class CreditCardService {
	
	@Autowired
	CreditCardRepository cardRepo;

}
