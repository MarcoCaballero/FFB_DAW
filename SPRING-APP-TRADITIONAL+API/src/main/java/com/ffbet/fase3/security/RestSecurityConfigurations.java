package com.ffbet.fase3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ffbet.fase3.repositories.UserRepositoryAuthenticationProvider;

@Configuration
@Order(1)
public class RestSecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");

		// URLs que necesitan autenticación (teams)
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/teams/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/teams/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/teams/**").hasAnyRole("ADMIN");
		// URLs que necesitan autenticación (matches)
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/matches/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/matches/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/matches/**").hasAnyRole("ADMIN");
		// URLs que necesitan autenticación (promos)
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/promos/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/promos/**").hasAnyRole("ADMIN");
		// URLs que necesitan autenticación (creditcard)
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/creditcard/**").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/creditcard/**").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/creditcard/**").hasAnyRole("USER");
		// URLs que necesitan autenticación (user)
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/**").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/user/**").hasAnyRole("ADMIN");

		// Otras URLs a las que se puede acceder sin identificarse
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		http.logout().logoutSuccessHandler((rq, rs, a) -> {
		});
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}

}
