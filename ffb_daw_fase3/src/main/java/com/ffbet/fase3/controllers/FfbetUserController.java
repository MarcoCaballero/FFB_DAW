package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffbet.fase3.domain.TemplatesPath;

/**
 *
 *
 */
@Controller
public class FfbetUserController extends RedirectController{

	/**
	 * Mapping index.html {@linkplain index}redirects the '/index/' URI to a '/index' URI to control the resources
	 * hierarchy .
	 * 
	 * @return a redirection
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
	
		String response = check_url(request, "user/index");
		return response;
	}

	@RequestMapping("/apuestasdeportivas")
	public String apuestasdeportivas(HttpServletRequest request) {
	
		String response = check_url(request, "user/apuestasdeportivas");
		return response;
	}

	@RequestMapping("/apuestasesports")
	public String apuestasesports() {
		return "user/apuestasesports";
	}

	@RequestMapping("/promociones")
	public String promociones() {
		return "user/promos";
	}

	@RequestMapping("/micuenta")
	public String micuenta() {
		return "user/micuenta";
	}

	@RequestMapping("/registrarse")
	public String registrarse() {
		return "user/reg";
	}

	@RequestMapping("depositar")
	public String depositar() {
		return "user/depositar";
	}

	@RequestMapping("/politica")
	public String politica() {
		return "user/politica";
	}

	@RequestMapping("/retirar")
	public String retirar() {
		return "user/retirar";
	}
	
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("isError", false);
		return "user/login";
	}
	
	@RequestMapping("/loginisError")
	public String loginErr(Model model) {
		model.addAttribute("isError", true);
		return "user/login";
	}
	
	@RequestMapping("/logoutuser")
	public String loginOut(Model model) {
		return "user/index";
	}
//	

}
