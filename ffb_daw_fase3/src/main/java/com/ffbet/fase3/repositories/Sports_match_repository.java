package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.SportsMatch;

public interface Sports_match_repository extends JpaRepository<SportsMatch, Long>{
	
	List<SportsMatch> findByType(String type);
	
}
