package com.ffbet.fase3.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.UserRepository;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {
    	
    	userRepository.save(new User("user", "surname", "12334-Z","user@hotmail.com", "pass", "ROLE_USER" ));
    	userRepository.save(new User("admin", "surname", "12334-Z","admin@hotmail.com", "passadmin", "ROLE_ADMIN" , "ROLE_USER"));
    }

}
