package com.ffbet.fase3.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.UserService;

@RestController
public class LoginRestController {

	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);

	@Autowired
	private UserService userService;

	@JsonView(User.Basico.class)
	@GetMapping("/api/logIn")
	public ResponseEntity<User> logIn() {
		User user = userService.handleUserLoggedFromComponent();
		if (user == null) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			log.info("Logged as " + user.getName());
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@GetMapping("/api/logOut")
	public ResponseEntity<Boolean> logOut(HttpSession session) {
		User user = userService.handleUserLoggedFromComponent();
		if (user == null) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

}
