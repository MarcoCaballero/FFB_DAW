package com.ffbet.fase3.api;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.CreditCard;
import com.ffbet.fase3.services.CreditCardService;

@RestController
@RequestMapping("/api/creditCard")
public class CreditCardRestController {
	
	@Autowired
	private CreditCardService service;

	/* GET ALL CREDIT CARDS */
	@GetMapping
	public Collection<CreditCard> getCreditCards() {
		return service.findAllCreditCards();
	}
	
	/* PUT CREDIT CARD FIND BY Id*/
	@PutMapping("/{id}")
	public ResponseEntity<CreditCard> updateCreditCardByID(HttpServletRequest request, 
			 @PathVariable long id, @RequestBody CreditCard updateCreditCard) {
			
		CreditCard creditCard =service.findOneCreditCard(id);
			
		if(creditCard != null){
			updateCreditCard.setId(id);
			service.saveCreditCard(updateCreditCard);
			return new ResponseEntity<>(creditCard, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	/* POST A NEW CREDIT CARD*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreditCard createCreditCard(@RequestBody CreditCard creditCard) {

		service.saveCreditCard(creditCard);

		return creditCard;
	}
	
	/* DELETE CREDIT CARD FIND BY Id*/
	@DeleteMapping("/{id}")
	public ResponseEntity<CreditCard> deleteCreditCard(@PathVariable long id) {
		
		
		CreditCard creditCard = service.findOneCreditCard(id);
		service.deleteCreditCard(id);
		
		if (creditCard != null){
			return new ResponseEntity<>(creditCard, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
