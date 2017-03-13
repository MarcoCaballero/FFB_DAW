package com.ffbet.fase3.repositories;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ffbet.fase3.domain.SportsMatch;

public interface Sports_match_repository extends JpaRepository<SportsMatch, Long>{
	
	@Query("SELECT u FROM SportsMatch u where u.isFinished = true AND u.type = :type")
	Page<SportsMatch> findByTypeFinished(@Param("type") String type, Pageable pageable);
	
	Page<SportsMatch> findByType(String type, Pageable pageable);

	List<SportsMatch> findByType(String type);
}
