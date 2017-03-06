/**
 * 
 */
package com.ffbet.fase3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ffbet.fase3.repositories.UserRepositoryAuthenticationProvider;


/**
 * @author Marco
 *
 */

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages

		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/signup/new").permitAll();

		http.authorizeRequests().antMatchers("/signup").permitAll();
//		"signup/new"

		// Private pages (all other pages)

		http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin/").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin-*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin-*/").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin-*/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin-*/*/").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin-*/*/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin-*/*/*/").hasAnyRole("ADMIN");

		http.authorizeRequests().antMatchers("/user").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user/").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user-*").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user-*/").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user-*/*").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user-*/*/").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user-*/*/*").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/user-*/*/*/").hasAnyRole("USER");

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/decideLogin");
		http.formLogin().failureUrl("/loginError");

		// Logout
		http.logout().logoutUrl("/logOut").deleteCookies("JSESSIONID", "remember-me");
		http.logout().logoutSuccessUrl("/login");

		// http.csrf().disable();
		http.csrf().ignoringAntMatchers("/h2-console/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}

}
