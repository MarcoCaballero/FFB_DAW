package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{
Promotion findById(long id);
 
 
 


}
