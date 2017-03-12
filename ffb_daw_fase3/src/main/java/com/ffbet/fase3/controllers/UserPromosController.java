/**
 * 
 */
package com.ffbet.fase3.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.PromotionRepository;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * @author Marco
 *
 */
@Controller
public class UserPromosController extends RedirectController{
	
	private String template = TemplatesPath.USER_PROMOTIONS.toString();
	private String redirect = "redirect:/user-promos/";

	@Autowired
	UserAuthComponent userComp;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PromotionRepository promoRepository;

	private boolean showsUserMenu = false;
	
	@GetMapping(value = { "/user-promos", "/user-promos/"})
	public String getTemplate(HttpServletRequest request, Model model) {
		if(userComp.isLoggedUser()){
			showsUserMenu  = true;
			model.addAttribute("user",  userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
		}else{
			showsUserMenu = false;
		}
		
		model.addAttribute("isUsermenuActive", showsUserMenu);
		
		model.addAttribute("PromotionDiscount", promoRepository.findByType("BONODESCUENTO"));
		model.addAttribute("PromotionPresent", promoRepository.findByType("PROMOCIONREGALO"));
		
		String response = check_url(request, template);
		return response;

	}
	
	@RequestMapping({"user-promos/subscribe/{id}"})
	public String addUserPromo(@PathVariable long id){
		User user = userRepo.findByEmail(userComp.getLoggedUser().getEmail());
		
		System.out.println(promoRepository.findOne(id).getTitle());
		
		try {
			Promotion promo = promoRepository.findOne(id);
			user.addPromo(promo);
			promo.setShown(true);
			
			for (Promotion p : promoRepository.findAll()) {
				if(!p.equals(promo)){
					p.setShown(false);
				}
			}
			
			promoRepository.save(promo);
			userRepo.save(user);
			
		} catch (Exception e) {
			
		}
		
		return redirect;
		
	}

}
