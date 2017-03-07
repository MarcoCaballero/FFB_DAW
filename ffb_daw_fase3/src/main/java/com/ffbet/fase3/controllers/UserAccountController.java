/**
 * 
 */
package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * @author Marco
 *
 */
@Controller
public class UserAccountController extends RedirectController{

	@Autowired
	UserAuthComponent userComp;
	
	@GetMapping(value = { "/user-account", "/user-account/"})
	public String getTemplate(HttpServletRequest request, Model model) {
		
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_USER_ACCOUNT.toString());
		return response;

	}

}
