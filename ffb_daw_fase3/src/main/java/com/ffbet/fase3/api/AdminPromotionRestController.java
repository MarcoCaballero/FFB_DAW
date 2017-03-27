package com.ffbet.fase3.api;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.services.PromoService;

@RestController
public class AdminPromotionRestController {

	@Autowired
	private PromoService promoService;

	@GetMapping(value = { "/admin-promotions/{id}", "/admin-promotions/{id}/" })
	public ResponseEntity<Promotion> getPromotionByID(HttpServletRequest request, @PathVariable long id) {

		Promotion promotion = promoService.findOne(id);

		if (promotion != null) {
			return new ResponseEntity<>(promotion, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * API REST Method {@linkplain deletePromotionByID()} delete the correct
	 * promotion from repository, and returns the promotion selected [selected
	 * by id] properties.
	 *
	 *
	 * @param request
	 * @param model
	 * @param id
	 * @return ResponseEntity<Promotion>
	 */
	@RequestMapping(value = { "/admin-promotions/{id}", "/admin-promotions/{id}/" }, method = RequestMethod.DELETE)
	public ResponseEntity<Promotion> deletePromotionByID(HttpServletRequest request, Model model,
			@PathVariable long id) {

		Promotion promotion = promoService.findOne(id);
		promoService.delete(id);

		if (promotion != null) {
			return new ResponseEntity<>(promotion, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * API REST Method {@linkplain updatePromotionByID()} update the correct
	 * promotion from repository, and returns the promotion selected [selected
	 * by id] properties.
	 *
	 *
	 * @param request
	 * @param model
	 * @param id
	 * @return ResponseEntity<Promotion>
	 */
	@RequestMapping(value = { "/admin-promotions/update/{id}",
			"/admin-promotions/update/{id}/" }, method = RequestMethod.PUT)
	public ResponseEntity<Promotion> updatePromotionByID(HttpServletRequest request, Model model, @PathVariable long id,
			@RequestBody Promotion updatePromotion) {

		Promotion promotion = promoService.findOne(id);

		if (promotion != null) {
			updatePromotion.setId(id);
			promoService.save(updatePromotion);
			return new ResponseEntity<>(promotion, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * API REST Method {@linkplain deletePromotionByID()} add a new promotion to
	 * the repository, and returns the promotion created [selected by id]
	 * properties.
	 *
	 *
	 * @param request
	 * @param model
	 * @param id
	 * @return ResponseEntity<Promotion>
	 */
	@RequestMapping(value = { "/admin-promotions/new", "/admin-promotions/new/" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Promotion addPromotion(HttpServletRequest request, @RequestBody Promotion newPromotion) {

		promoService.save(newPromotion);

		return newPromotion;
	}

	/**
	 * API REST Method {@linkplain getPromotions()} get all existing promotions
	 * from the repository, and returns a collections of them. properties.
	 *
	 *
	 * @param request
	 * @param model
	 * @param id
	 * @return Collection<Promotion>
	 */
	@RequestMapping(value = { "/admin-promotions/promotions",
			"/admin-promotions/promotions/" }, method = RequestMethod.GET)
	public Collection<Promotion> getPromotions() {
		System.out.println("as");
		return promoService.findAll();
	}

}
