package com.ffbet.fase3.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.services.PromoService;

@RestController
@RequestMapping("/api/promos")
public class AdminPromotionRestController {
	
	@Autowired
	private PromoService promoService;
	
	// mostrar promociones
	@RequestMapping(value = "/getPromos", method = RequestMethod.GET)
	public List<Promotion> findAll(){
		return promoService.findAll();
	}
	
	// añadir promoción
	@RequestMapping(value = "/newPromo", method = RequestMethod.PUT)
	public Promotion newPromotion(Promotion promo){
		promoService.save(promo);
		
		return promo;
	}
	
	// eliminar promoción por id
	@RequestMapping(value = "/deletePromo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Promotion> deletePromotion(@PathVariable long id){
		Promotion promo = promoService.findOne(id);
		
		if(promo != null){
			promoService.delete(id);
			
			return new ResponseEntity<>(promo, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
