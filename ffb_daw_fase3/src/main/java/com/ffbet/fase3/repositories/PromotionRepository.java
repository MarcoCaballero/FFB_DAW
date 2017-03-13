package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.Promotion;
import java.lang.String;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	Promotion findById(long id);

	List<Promotion> findByTitle(String title);

	List<Promotion> findByType(String type);

	List<Promotion> findByPromotionCode(String promotioncode);
}
