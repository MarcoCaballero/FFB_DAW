package com.ffbet.fase3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FfbetAdminController {

	
	@RequestMapping("/admin")
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
