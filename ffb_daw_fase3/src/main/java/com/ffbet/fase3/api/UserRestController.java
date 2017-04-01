package com.ffbet.fase3.api;

import java.util.List;

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
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.services.CreditCardService;
import com.ffbet.fase3.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CreditCardService cardService;
	
	@GetMapping
	public List<User> getUsers(){
		return userService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user){
		userService.save(user);
		
		return user;
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(){
		User user = userService.handleUserLoggedFromComponent();
		
		if (user != null){
			//updatedUser.setId(user.getId());
			userService.updateUser(user);
			
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id){
		User user = userService.findOne(id);
		
		if(user != null){
			userService.delete(id);
			
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/creditCardPlus/{amount}")
	public ResponseEntity<CreditCard> moreUserCredit(@PathVariable String amount, @RequestBody CreditCard creditCard){
		boolean error = false;
		
		cardService.saveCreditCard(creditCard, amount, error);
		
		if(!error){
			return new ResponseEntity<>(creditCard, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping("/creditCardLess/{amount}")
	public ResponseEntity<CreditCard> lessUserCredit(@PathVariable String amount, @RequestBody CreditCard creditCard){
		CreditCard cd = cardService.getCard(creditCard.getCardNumber());
		if(cd != null && cd.getCredit() < Double.parseDouble(amount)){
			cardService.takeCredit(creditCard, amount);
		
			return new ResponseEntity<>(creditCard, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
