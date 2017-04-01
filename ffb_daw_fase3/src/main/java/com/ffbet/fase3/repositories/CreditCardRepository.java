package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	
	List<CreditCard> findByCardNumber(String cardNumber);

}
