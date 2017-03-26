package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.repositories.PromotionRepository;

@Service
public class PromoService {
	
	@Autowired
	PromotionRepository promoRepo;
	
	// findAll
	public List<Promotion> findAll(){
		return promoRepo.findAll();
	}
	
	// findOne
	public Promotion findOne(long id){
		return promoRepo.findOne(id);
	}
	
	// save
	public void save(Promotion promo){
		promoRepo.save(promo);
	}
	
	// delete
	public void delete(long id){
		promoRepo.delete(id);
	}
	
	// findByTitle
	public List<Promotion> findByTitle(String title){
		return promoRepo.findByTitle(title);
	}
	
	// findByTpe
	public List<Promotion> findByType(String type){
		return promoRepo.findByType(type);
	}
	
	// findByPromotionCode
	public List<Promotion> findByPromotionCode(String code){
		return promoRepo.findByPromotionCode(code);
	}

}
