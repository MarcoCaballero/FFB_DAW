package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FfbetAdminController {

	@RequestMapping("/cpanelhome")
	public String cpanelhome(){
		return "admin/home";
	}
	
	@RequestMapping("/cpanelequipo")
	public String cpanelequipo(){
		return "admin/equipos";
	}
	
	@RequestMapping("/cpanellogin")
	public String cpanellogin(){
		return "admin/login";
	}
	
	@RequestMapping("/cpanelpartido")
	public String cpanelpartido(){
		return "admin/partidos";
	}
	
	@RequestMapping("/cpanelpromocion")
	public String cpanelpromocion(){
		return "admin/promociones";
	}
	
	@RequestMapping("/cpanelresultado")
	public String cpanelresultado(){
		return "admin/resultados";
	}
}
