package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ffbet.fase3.domain.EgamesMatch;

public interface Egames_match_repository extends JpaRepository<EgamesMatch, Long>{
	
	List<EgamesMatch> findByType(String type);
	Page<EgamesMatch> findByType(String type, Pageable pageable);
	
	@Query("SELECT u FROM EgamesMatch u where u.isFinished = true AND u.type = :type")
	Page<EgamesMatch> findByTypeFinished(@Param("type") String type, Pageable pageable);
}
