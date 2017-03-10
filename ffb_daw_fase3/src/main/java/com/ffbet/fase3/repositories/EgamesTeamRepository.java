package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.EgamesTeam;
import java.lang.String;
import java.util.List;

public interface EgamesTeamRepository extends JpaRepository<EgamesTeam, Long> {
	
	EgamesTeam findByName(String name);
	List<EgamesTeam> findByType(String type);

}
