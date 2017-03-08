package com.ffbet.fase3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.SportTeam;

public interface SportTeamRepository extends JpaRepository<SportTeam, Long> {

	SportTeam findByName(String name);
	
}
