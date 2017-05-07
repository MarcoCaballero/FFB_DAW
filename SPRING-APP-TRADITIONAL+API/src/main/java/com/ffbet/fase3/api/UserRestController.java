package com.ffbet.fase3.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.CreditCard;
import com.ffbet.fase3.domain.FilesPath;
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
	public List<User> getUsers() {
		return userService.findAll();
	}

	// Obtener usuario por id
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		User user = userService.findOne(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user) {
		userService.save(user);

		return user;
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		if (user != null) {
			userService.updateUser(user);

			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/uploadImage", consumes = "multipart/form-data")
	public String handleFileUpload(@RequestPart("file") MultipartFile file) throws IOException {
		User user = userService.handleUserLoggedFromComponent();

		String filename = userService.handleUploadImagetoDatabase(file, user.getId(),
				FilesPath.FILES_AVATARS.toString());

		user.setPhoto_url(filename);
		user.setPhotoSelected(true);
		userService.updateUser(user);
		return filename;
	}

	@GetMapping("/avatar/{id}")
	public ResponseEntity<HttpStatus> getAvatarImage(@PathVariable long id, HttpServletResponse response)
			throws FileNotFoundException, IOException {

		User user = userService.findOne(id);

		if (user != null && user.getPhoto_url() != null) {
			response.addHeader("Content-type", "image/jpeg");
			InputStream file = userService.handleFileDownload(response, user.getPhoto_url(), "avatars");
			FileCopyUtils.copy(file, response.getOutputStream());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		User user = userService.findOne(id);

		if (user != null) {
			userService.delete(id);

			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/* Credit card zone */

	@PutMapping("/creditCardPlus/{amount}")
	public ResponseEntity<CreditCard> moreUserCredit(@PathVariable String amount, @RequestBody CreditCard creditCard) {
		boolean error = false;

		CreditCard card = cardService.saveCreditCard(creditCard, amount);
		if (card == null) {
			error = true;
		}
		if (!error) {
			return new ResponseEntity<>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/creditCardLess/{amount}")
	public ResponseEntity<CreditCard> lessUserCredit(@PathVariable String amount, @RequestBody CreditCard creditCard) {
		CreditCard cd = cardService.getCard(creditCard.getCardNumber());

		if (cd != null && cd.getCredit() > Double.parseDouble(amount)) {
			cardService.takeCredit(cd, amount);

			return new ResponseEntity<>(cd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/creditCards/{id}")
	public List<CreditCard> getCreditCards(@PathVariable long id) {
		User user = userService.findOne(id);
		return user.getCards();
	}
	
	@GetMapping("/tickets/{id}")
	public List<BetTicket> getTicketsPerUser(@PathVariable long id){
		User user = userService.findOne(id);
		return user.getBet_tickets();
	}

}
