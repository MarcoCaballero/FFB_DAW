package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.EgamesTeam;

public interface Egames_match_repository extends JpaRepository<EgamesMatch, Long>{
	
	List<EgamesTeam> findByType(String type);
	Page<EgamesMatch> findByType(String type, Pageable pageable);
}
