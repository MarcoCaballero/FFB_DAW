package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * Controller class {@link UserHomeController} provides methods to map the URL's
 * that reference to the HOME, LOGIN, REGISTER, and other main functions. This
 * controller also extends to an Abstract class {@link RedirectController} that
 * provides methods common to several controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class UserHomeController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;
	private boolean showsUserMenu = false;

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the HOME template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/user", "/user/", "/user/*" })
	public String getTemplate(HttpServletRequest request, Model model) {
		
		if(userComp.isLoggedUser()){
			showsUserMenu = true;
			model.addAttribute("user", userComp.getLoggedUser());
			if(!userComp.getLoggedUser().isPhotoSelected()){
				model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			}else{
				//Use image controller
			}
		}else{
			showsUserMenu = false;
		}
		
		
		model.addAttribute("isUsermenuActive", showsUserMenu);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_HOME.toString());
		return response;

	}

}
