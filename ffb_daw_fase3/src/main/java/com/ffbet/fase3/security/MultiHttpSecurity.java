/**
 * 
 */
package com.ffbet.fase3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Marco
 *
 */

@EnableWebSecurity
public class MultiHttpSecurity {
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}


	@Configuration
	@Order(1)
	public static class AdminSecurityConfigurations extends WebSecurityConfigurerAdapter {
		

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// Public pages
			
			http.authorizeRequests().antMatchers("/admin-login").permitAll();
			http.authorizeRequests().antMatchers("/logout").permitAll();

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
			http.formLogin().loginPage("/admin-login");
			http.formLogin().usernameParameter("username");
			http.formLogin().passwordParameter("password");
			http.formLogin().defaultSuccessUrl("/admin");
			http.formLogin().failureUrl("/loginerror");

			// Logout
			http.logout().logoutUrl("/logout").deleteCookies("JSESSIONID","remember-me");
			http.logout().logoutSuccessUrl("/admin-login");

			// http.csrf().disable();
		}

		
	}

	@Configuration
	public static class UserSecurityConfigurations extends WebSecurityConfigurerAdapter {
		

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// Public pages
			http.authorizeRequests().antMatchers("/registrarse").permitAll();
			http.authorizeRequests().antMatchers("/logout").permitAll();
			http.authorizeRequests().antMatchers("/index").permitAll();

			// Private pages (all other pages)
			http.authorizeRequests().antMatchers("/apuestasdeportivas").hasAnyRole("USER");
			http.authorizeRequests().antMatchers("/apuestasesports").hasAnyRole("USER");
			http.authorizeRequests().antMatchers("/promociones").hasAnyRole("USER");
			http.authorizeRequests().antMatchers("/micuenta").hasAnyRole("USER");

			// Login form
			http.formLogin().loginPage("/login");
			http.formLogin().usernameParameter("username");
			http.formLogin().passwordParameter("password");
			http.formLogin().defaultSuccessUrl("/index");
			http.formLogin().failureUrl("/loginisError");

			// Logout
			http.logout().logoutUrl("/logoutuser").deleteCookies("JSESSIONID","remember-me");
			http.logout().logoutSuccessUrl("/login");
			http.logout().permitAll();

			// http.csrf().disable();
		}

		
	}

}
