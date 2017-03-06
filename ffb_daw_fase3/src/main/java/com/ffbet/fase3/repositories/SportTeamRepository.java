package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.SportTeam;

public interface SportTeamRepository extends JpaRepository<SportTeam, Long> {

	List<SportTeam> findByName(String name);
	SportTeam findById(Long id);
	
}
