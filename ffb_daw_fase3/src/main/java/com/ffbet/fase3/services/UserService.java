package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public User findOne(long id){
		return userRepo.findOne(id);
	}
	
	public User findByName(String name){
		return userRepo.findByName(name);
	}
	
	public User findByEmail(String email){
		return userRepo.findByEmail(email);
	}
	
	public void save(User user){
		userRepo.save(user);
	}
	
	public void delete(long id){
		userRepo.delete(id);
	}

}
