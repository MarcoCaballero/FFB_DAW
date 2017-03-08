package com.ffbet.fase3.controllers;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.repositories.PromotionRepository;




@Controller
public class AdminPromotionController extends RedirectController{
	
	private String template = TemplatesPath.ADMIN_PROMOTION.toString();
	private String redirect = "redirect:/admin-promotions/";
	
	@Autowired
	private PromotionRepository repository;
	
	@PostConstruct
	public void init(){
		repository.save(new Promotion("Deporte","Hay gol","gomasdaea",""));
		repository.save(new Promotion("eSports","frikiss","eso213o",""));
		repository.save(new Promotion("Deporte","Que tal","que pasa",""));
		repository.save(new Promotion("Deporte","Cris marica","mariconn",""));
		repository.save(new Promotion("eSports","frikiss2","dildo",""));
		
	}

	/**
	 * Method {@linkplain getPromotionsTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the PROMOTIONS template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return response
	 */
	@GetMapping(value = { "/admin-promotions", "/admin-promotions/"})
	public String getPromotionsTemplate(HttpServletRequest request, Model model) {
		
		model.addAttribute("promotions", repository.findAll());
		String response = check_url(request, template);
		return response;

	}
	
//	/**  API REST
//	 * Method {@linkplain getPromotionByID()} get the correct template from similar URLs,
//	 * and shows it through the browser with the match [selected by id]
//	 * properties.
//	 * 
//	 * 
//	 * @param request
//	 * @param model
//	 * @param id
//	 * @return ResponseEntity<Promotion>
//	 */
//	@GetMapping(value = { "/admin-promotions/{id}", "/admin-promotions/{id}/" })
//	public ResponseEntity<Promotion> getPromotionByID(HttpServletRequest request, @PathVariable long id) {
//		
//		
//		Promotion promotion = repository.findOne(id);
//		
//		if(promotion != null){
//			return new ResponseEntity<>(promotion, HttpStatus.OK);
//		}else{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	
//	}

	
	
//	/**		API REST
//	 * Method {@linkplain deletePromotionByID()} delete the correct promotion from repository,
//	 * and returns the promotion selected [selected by id]
//	 * properties.
//	 * 
//	 * 
//	 * @param request
//	 * @param model
//	 * @param id
//	 * @return ResponseEntity<Promotion>
//	 */
//	@RequestMapping(value = { "/admin-promotions/{id}", "/admin-promotions/{id}/" }, method = RequestMethod.DELETE)
//	public ResponseEntity<Promotion> deletePromotionByID(HttpServletRequest request, Model model, @PathVariable long id) {
//		
//		
//		Promotion promotion =repository.findOne(id);
//		repository.delete(id);
//		
//		if(promotion != null){
//			return new ResponseEntity<>(promotion, HttpStatus.OK);
//		}else{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	
//	}
	/**	
	 * Method {@linkplain deletePromotionByID()} delete a specific promotion from the repository,
	 * and returns the template
	 * properties.
	 * 
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = { "/admin-promotions/delete/{id}", "/admin-promotions/delete/{id}/" })
	public String deletePromotionByID(HttpServletRequest request, Model model, @PathVariable long id) {
		
		repository.delete(id);
		model.addAttribute("promotions", repository.findAll());
		return redirect;
	
	}
	
	
//	/**		API REST
//	 * Method {@linkplain updatePromotionByID()} update the correct promotion from repository,
//	 * and returns the promotion selected [selected by id]
//	 * properties.
//	 * 
//	 * 
//	 * @param request
//	 * @param model
//	 * @param id
//	 * @return ResponseEntity<Promotion>
//	 */
//	@RequestMapping(value = { "/admin-promotions/update/{id}", "/admin-promotions/update/{id}/" }, method = RequestMethod.PUT)
//	public ResponseEntity<Promotion> updatePromotionByID(HttpServletRequest request, Model model, @PathVariable long id, @RequestBody Promotion updatePromotion) {
//		
//		
//		Promotion promotion =repository.findOne(id);
//		
//		if(promotion != null){
//			updatePromotion.setId(id);
//			repository.save(updatePromotion);
//			return new ResponseEntity<>(promotion, HttpStatus.OK);
//		}else{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	
//	}
	
//	/**		API REST
//	 * Method {@linkplain deletePromotionByID()} add a new promotion to the repository,
//	 * and returns the promotion created [selected by id]
//	 * properties.
//	 * 
//	 * 
//	 * @param request
//	 * @param model
//	 * @param id
//	 * @return ResponseEntity<Promotion>
//	 */
//	@RequestMapping(value = { "/admin-promotions/new", "/admin-promotions/new/" }, method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.CREATED)
//	public Promotion addPromotion(HttpServletRequest request, @RequestBody Promotion newPromotion) {
//		
//		repository.save(newPromotion);
//
//		return newPromotion;
//	}
	
	/**	
	 * Method {@linkplain addPromotion()} add a new promotion to the repository,
	 * and returns the template
	 * properties.
	 * 
	 * 
	 * @param request
	 * @param model
	 * @param type
	 * @param title
	 * @param description
	 * @param promotionCode
	 * @return String
	 */
	@PostMapping(value = { "/admin-promotions/new", "/admin-promotions/new/"})
	public String addPromotion(Model model, HttpServletRequest request, @RequestParam String type, 
			@RequestParam String promotionCode, @RequestParam String title, @RequestParam String description) {
		
		Promotion newPromotion = new Promotion(type,title,description,promotionCode);
		
		repository.save(newPromotion);
		
		model.addAttribute("promotions", repository.findAll());
		return redirect;
	}
	
//	/**		API REST
//	 * Method {@linkplain getPromotions()} get all existing promotions from the repository,
//	 * and returns a collections of them.
//	 * properties.
//	 * 
//	 * 
//	 * @param request
//	 * @param model
//	 * @param id
//	 * @return Collection<Promotion>
//	 */
//	@RequestMapping(value = { "/admin-promotions/promotions", "/admin-promotions/promotions/" }, method= RequestMethod.GET)
//	public Collection<Promotion> getPromotions() {
//		System.out.println("as");
//		return repository.findAll();
//	}
}
