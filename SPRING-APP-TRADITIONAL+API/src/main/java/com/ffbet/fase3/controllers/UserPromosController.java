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
import com.ffbet.fase3.domain.PromotionType;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.PromoService;
import com.ffbet.fase3.services.UserService;

/**
 * @author Marco
 *
 */
@Controller
public class UserPromosController extends RedirectController {

	private String template = TemplatesPath.USER_PROMOTIONS.toString();
	private String redirect = "redirect:/user-promos/";
	
	@Autowired
	private UserService userService;
	@Autowired
	private PromoService promoService;

	@Autowired
	UserAuthComponent userComp;

	private boolean showsUserMenu = false;
	private boolean showsPromoError = false;

	@GetMapping(value = { "/user-promos", "/user-promos/" })
	public String getTemplate(HttpServletRequest request, Model model) {
		showsUserMenu = false;
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		}
		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("showsPromoError", showsPromoError);

		model.addAttribute("PromotionDiscount", promoService.findByType("BONODESCUENTO"));
		model.addAttribute("PromotionPresent", promoService.findByType("PROMOCIONREGALO"));

		String response = check_url(request, template);
		return response;

	}

	@RequestMapping({ "user-promos/subscribe/{id}" })
	public String addUserPromo(@PathVariable long id) {

		if (userComp.isLoggedUser()) {
			User user = userService.findByEmail(userComp.getLoggedUser().getEmail());
			try {
				Promotion promo = promoService.findOne(id);

				promo.setShown(true);

				for (Promotion p : promoService.findAll()) {
					if (!p.equals(promo)) {
						p.setShown(false);
					}
				}

				if (promo.getType().equals(PromotionType.PROMO_GIFT.toString())) {
					showsPromoError = true;
					if (!user.getPromos().contains(promo)) {
						user.addPromotionCredit(promo.getQuantity());
						promo.setShown(false);
						showsPromoError = false;
					}
				}
				user.addPromo(promo);
				userService.updateUser(user);

			} catch (Exception e) {

			}

		}

		return redirect;

	}

}
