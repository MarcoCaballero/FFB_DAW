package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *
 */
@Controller
public class FfbetUserController {

	/**
	 * Mapping index.html {@linkplain index}redirects the '/index/' URI to a '/index' URI to control the resources
	 * hierarchy .
	 * 
	 * @return a redirection
	 */
	@RequestMapping(value = { "/index/prueba", "/index/prueba/" })
	public String index(HttpServletRequest request) {
		
		switch (request.getRequestURI()) {
		case "/index/prueba":
			return "user/index";

		case "/index/prueba/":
			return "redirect:../index";

		default:
			return null;

		}

	}

	@RequestMapping("/apuestasdeportivas")
	public String apuestasdeportivas() {
		return "user/apuestasdeportivas";
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

	@RequestMapping("retirar")
	public String retirar() {
		return "user/retirar";
	}
}
