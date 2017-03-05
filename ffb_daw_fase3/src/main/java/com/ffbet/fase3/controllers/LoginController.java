/**
 * 
 */
package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.repositories.UserRepositoryAuthenticationProvider;

/**
 * @author Marco
 *
 */
@Controller
public class LoginController extends RedirectController {

	String templateLogin = TemplatesPath.MAIN_LOGIN.toString();
	String templateSignup = TemplatesPath.MAIN_REG.toString();
	String redirectLogin = "redirect:/login/";
	String redirectUserHome = "redirect:/user/";
	String redirectAdminHome = "redirect:/admin/";
	String redirectSignup = "redirect:/signup/";

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the LOGIN template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/login", "/login/" })
	public String getLoginTemplate(HttpServletRequest request, Model model) {
		model.addAttribute("isError", false);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, templateLogin);
		//
		return response;

	}

	@RequestMapping("/decideLogin")
	public String decideLogin(HttpServletRequest request, Model model) {
		System.out.println("SOY USUARIO :" + request.isUserInRole("ROLE_ADMIN"));
		if (request.isUserInRole("ROLE_ADMIN"))
			return redirectAdminHome;

		return redirectUserHome;

	}

	@RequestMapping("/loginError")
	public String loginError(HttpServletRequest request, Model model) {

		model.addAttribute("isError", true);
		return redirectLogin;
	}

	@RequestMapping("/logOut")
	public String loginOut(HttpServletRequest request, Model model) {

		model.addAttribute("isError", false);
		return redirectLogin;
	}

	@GetMapping(value = { "/signup", "/signup/" })
	public String singUpTemplate(HttpServletRequest request, Model model) {
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, templateSignup);
		return response;

	}

	@PostMapping("signup/new")
	public String addUser() {
		return redirectSignup;

	}

}
