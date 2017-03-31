/**
 * 
 */
package com.ffbet.fase3.controllers;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.UserService;

/**
 * Controller class {@link LoginController} provides methods to map the URL's
 * that reference to the LOGIN, REGISTER, and other main functions. This
 * controller also extends to an Abstract class {@link RedirectController} that
 * provides methods common to several controllers
 * 
 * 
 * @see {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class LoginController extends RedirectController {

	private String templateLogin = TemplatesPath.MAIN_LOGIN.toString();
	private String templateSignup = TemplatesPath.MAIN_REG.toString();
	private String redirectLogin = "redirect:/login/";
	private String redirectUserHome = "redirect:/user/";
	private String redirectAdminHome = "redirect:/admin/";
	private String redirectSignup = "redirect:/signup/";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserAuthComponent userComponent;

	private boolean isErrorLogin = false;
	private boolean isErrorPass = false;
	private boolean showsUserMenu = false;


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
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		model.addAttribute("isError", isErrorLogin);
		String response = check_url(request, templateLogin);
		//
		return response;

	}

	@RequestMapping("/decideLogin")
	public String decideLogin(HttpServletRequest request, Model model) {

		isErrorLogin = false;
		if (!userComponent.isLoggedUser()) {
			model.addAttribute("isError", true);
			return redirectLogin;
		} else {
			if (userComponent.getLoggedUser().getRoles().get(0).equals("ROLE_ADMIN"))
				return redirectAdminHome;

			return redirectUserHome;
		}

	}

	@RequestMapping("/loginError")
	public String loginError(HttpServletRequest request, Model model) {

		isErrorLogin = true;
		return redirectLogin;
	}

	@RequestMapping("/logOut")
	public String loginOut(HttpServletRequest request, Model model, HttpSession session) {
		isErrorLogin = false;
		if (!userComponent.isLoggedUser()) {
			isErrorLogin = false;
			return redirectLogin;
		} else {
			session.invalidate();
			return redirectLogin;
		}

	}

	@GetMapping(value = { "/signup", "/signup/" })
	public String singUpTemplate(HttpServletRequest request, Model model) {

		model.addAttribute("isPassOkey", isErrorPass);

		// model.addAttribute("isPassOkey", true);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, templateSignup);
		return response;

	}

	@PostMapping("signup/new")
	public String addUser(ModelAndView model, @RequestParam("telf") String telephone, @RequestParam("pass") String pass,
			@RequestParam("passRepeat") String passRepeat, @RequestParam("sex") String sex,
			@RequestParam("secondSurname") String secondSurname, User user) {
		String redirectFromRole = redirectSignup;
		System.out.println("PASS " + pass + " PASSREPEAT :" + passRepeat);

		if (pass.equals(passRepeat)) {
			System.out.println("HOLA CONTRASEÑAS IGUALES");
			isErrorPass = false;
			user.setPassword(pass);

			if ((telephone != null) && telephone != "")
				user.setTelephone(telephone);

			if ((secondSurname != null) && secondSurname != "") {
				user.setSecondSurname(secondSurname);
			}

			if (userComponent.isLoggedUser()) {
				if (userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")) {
					redirectFromRole = redirectAdminHome;
				}
			} else {
				redirectFromRole = redirectSignup;
			}

			if (sex.equals("MAN")) {
				user.setMen(true);
			} else {
				user.setMen(false);
			}

			userService.save(user);
		} else {
			System.out.println("HOLA CONTRASEÑAS DISTINTAS");
			isErrorPass = true;
			return redirectSignup;
		}

		return redirectFromRole;

	}

	@GetMapping("/decideDenied")
	public String decideDenied() {
		if (userComponent.isLoggedUser()) {
			if (userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")) {
				return redirectAdminHome;
			} else {
				return redirectUserHome;
			}
		} else {

			return redirectSignup;
		}

	}

	// ffbet/policy-terms

	@GetMapping("/ffbet/policy-terms")
	public String policy(Model model) {
		showsUserMenu = false;
		if (userComponent.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userService.findByEmail(userComponent.getLoggedUser().getEmail()));
		}
		
		
		model.addAttribute("isUsermenuActive", showsUserMenu);
		return TemplatesPath.USER_SEE_POLICY.toString();
	}
}
