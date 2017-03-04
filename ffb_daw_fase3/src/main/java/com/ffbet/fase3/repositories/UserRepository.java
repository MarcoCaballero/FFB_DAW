package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.User;
/**
 * @author Marco
 *
 */
import java.lang.String;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
