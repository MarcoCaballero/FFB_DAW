package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ffbet.fase3.domain.Match;
import com.ffbet.fase3.domain.SportsMatch;

public interface MatchRepository extends JpaRepository<Match, Long>{
	
	@Query("SELECT u FROM Match u where u.isFinished = true AND u.type = :type")
	List<SportsMatch> findByFinished(@Param("type") String type);
	
	@Query("SELECT u FROM Match u where u.isFinished = false AND u.type = :type")
	List<SportsMatch> findByNotFinished(@Param("type") String type);
	
	@Query("SELECT u FROM SportsMatch u where u.isFinished = false AND u.type = :type AND (u.homeTeam = :nameTeam OR visitingTeam = :nameTeam)")
	List<SportsMatch> findByNotFinishedAndTeam(@Param("type") String type, @Param("nameTeam") String nameTeam);
	
	@Query("SELECT u FROM SportsMatch u where u.isFinished = true AND u.type = :type AND (u.homeTeam = :nameTeam OR visitingTeam = :nameTeam)")
	List<SportsMatch> findByFinishedAndTeam(@Param("type") String type, @Param("nameTeam") String nameTeam);

}
