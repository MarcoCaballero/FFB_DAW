package com.ffbet.fase3.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthComponent userComponent;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String email = auth.getName();
		String password = (String) auth.getCredentials();

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new BadCredentialsException("User not found");
		}

		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {

			throw new BadCredentialsException("Wrong password");
		} else {

			userComponent.setLoggedUser(user);

			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role : user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
			}

			return new UsernamePasswordAuthenticationToken(email, password, roles);
		}
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
