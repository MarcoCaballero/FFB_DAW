package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ffbet.fase3.domain.UserAdminNames;
import com.ffbet.fase3.domain.UserAdminPasswds;

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
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/home");
		return response;

	}

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the LOGIN template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-login", "/admin-login/" })
	public String getLoginTemplate(HttpServletRequest request, Model model) {
		// Checks the resources context.
		model.addAttribute("resources", checkResourcesContext(request));
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, "admin/login");
		String name = "HOSE";
		String passwd = "ADMIN";
		boolean isOkName = false;
		boolean isOkPasswd = false;
		UserAdminNames userName = null;
		UserAdminPasswds userPass = null;
		boolean allowLogin = false;

		for (UserAdminNames userN : UserAdminNames.values()) {
			if (userN.equalsNames(name)) {
				isOkName = true;
				userName = userN;
			}

		}
		for (UserAdminPasswds userP : UserAdminPasswds.values()) {
			if (userP.equalsPasswords(passwd)) {
				isOkPasswd = true;
				userPass = userP;
			}
		}

		if (userName!=null && userPass!=null) {
			if (userName.name().toString().equals(userPass.name().toString()) && isOkName && isOkPasswd) {
				allowLogin = true;
			}
		}

		System.out.println("HOLA ESTAS DENTRO ??" + allowLogin);

		return response;

	}
}
