/**
 * 
 */
package com.ffbet.fase3.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * @author Marco
 *
 */
@Controller
public class UserSportsBetController extends RedirectController {

	@Autowired
	UserAuthComponent userComp;

	@Autowired
	UserRepository userRepo;
	private boolean showsUserMenu = false;
	private boolean isFirstCome = true;

	@GetMapping(value = { "/user-sportsBet", "/user-sportsBet/" })
	public String getTemplate(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {

		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			User updatedUser = userRepo.findByEmail(userComp.getLoggedUser().getEmail());
			model.addAttribute("user", updatedUser);
			
			if(isFirstCome){
				response.addCookie(new Cookie("ffbetSession", "UserFFB" + updatedUser.getId()));
				isFirstCome = !isFirstCome;
			}
			System.out.println("ffbetSession " + WebUtils.getCookie(request, "ffbetSession"));
			
			if (!userComp.getLoggedUser().isPhotoSelected()) {
				// model.addAttribute("isMen",
				// userComp.getLoggedUser().isMen());
			} else {
				// Use image controller
			}
		} else {
			showsUserMenu = false;
		}

		model.addAttribute("isUsermenuActive", showsUserMenu);

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String responseTemplate = check_url(request, TemplatesPath.USER_SPORT_BET.toString());
		return responseTemplate;

	}

}
