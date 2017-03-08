package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.SportsMatch;

public interface Egames_match_repository extends JpaRepository<EgamesMatch, Long>{
	List<EgamesMatch> findByType(String type);
}
