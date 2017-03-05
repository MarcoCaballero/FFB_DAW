package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import  com.ffbet.fase3.domain.TemplatesPath;


/**
 * Controller class {@link AdminHomeController} provides methods to map the
 * URL's that reference to the HOME, LOGIN, REGISTER, and other main functions.
 * This controller also extends to an Abstract class {@link RedirectController}
 * that provides methods common to several controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class AdminHomeController extends RedirectController {

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the HOME template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-home", "/admin-home/", "/admin", "/admin/" })
	public String getTemplate(HttpServletRequest request, Model model) {
			// Checks the URLs with "/*" pattern
			// Delete the last bar if the requested URL is like "/*/"
			String response = check_url(request, TemplatesPath.ADMIN_HOME.toString());
			return response;
		
		

	}

	
}
