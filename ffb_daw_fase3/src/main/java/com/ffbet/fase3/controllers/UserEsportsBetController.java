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
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * @author Marco
 *
 */
@Controller
public class UserEsportsBetController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;

	@Autowired
	UserRepository userRepo;
	private boolean showsUserMenu = false;

	@GetMapping(value = { "/user-EsportsBet", "/user-EsportsBet/" })
	public String getTemplate(HttpServletRequest request, Model model) {
		if(userComp.isLoggedUser()){
			showsUserMenu  = true;

			model.addAttribute("user",  userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
			if(!userComp.getLoggedUser().isPhotoSelected()){
				//model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			}else{
				//Use image controller
			}
		}else{
			showsUserMenu = false;
		}
		
		
		model.addAttribute("isUsermenuActive", showsUserMenu);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_EGAMES_BET.toString());
		return response;

	}

}
