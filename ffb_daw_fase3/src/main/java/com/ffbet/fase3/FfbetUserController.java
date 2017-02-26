package com.ffbet.fase3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FfbetUserController {

	@RequestMapping("/index")
	public String index(){
		return "user/index";
	}
	
	@RequestMapping("/apuestasdeportivas")
	public String apuestasdeportivas(){
		return "user/apuestasdeportivas";
	}
	
	@RequestMapping("/apuestasesports")
	public String apuestasesports(){
		return "user/apuestasesports";
	}
	
	@RequestMapping("/promociones")
	public String promociones(){
		return "user/promos";
	}
	
	@RequestMapping("/micuenta")
	public String micuenta(){
		return "user/micuenta";
	}
	
	@RequestMapping("/registrarse")
	public String registrarse(){
		return "user/reg";
	}
	
	@RequestMapping("depositar")
	public String depositar(){
		return "user/depositar";
	}
	
	@RequestMapping("/politica")
	public String politica(){
		return "user/politica";
	}
	
	@RequestMapping("retirar")
	public String retirar(){
		return "user/retirar";
	}
}
