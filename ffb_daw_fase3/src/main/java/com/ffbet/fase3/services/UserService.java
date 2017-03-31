package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserAuthComponent userComp;

	/* USER REPOSITORY METHODS */

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findOne(long id) {
		return userRepo.findOne(id);
	}

	public User findByName(String name) {
		return userRepo.findByName(name);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public void save(User user) {
		if (userComp.isLoggedUser()) {
			if (userComp.getLoggedUser().getRoles().contains("ROLE_ADMIN"))
				user.setRoles("ROLE_ADMIN");
		} else {
			user.setRoles("ROLE_USER");
		}

		user.setCredit(0.0);
		userRepo.save(user);

	}
	
	public void updateUser(User user){
		userRepo.save(user);
	}

	public void delete(long id) {
		userRepo.delete(id);
	}

	/* USER LOGGED METHODS */

	public User handleUserLoggedFromComponent() {

		User userLogged = null;
		if (userComp.isLoggedUser()) {
			userLogged = findByEmail(userComp.getLoggedUser().getEmail());
			save(userLogged);
		}
		return userLogged;
	}

}
