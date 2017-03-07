package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByEmail(String email);
}
