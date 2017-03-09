package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.SportTeam;

public interface EgamesTeamRepository extends JpaRepository<EgamesTeam, Long> {
	
	EgamesTeam findByName(String name);

}
