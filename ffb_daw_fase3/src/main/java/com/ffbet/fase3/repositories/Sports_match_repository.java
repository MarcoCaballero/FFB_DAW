package com.ffbet.fase3.repositories;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.SportsMatch;

public interface Sports_match_repository extends JpaRepository<SportsMatch, Long>{
	
	Page<SportsMatch> findByType(String type, Pageable pageable);

	List<SportsMatch> findByType(String type);
}
