package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ffbet.fase3.domain.SportTeam;
import java.lang.String;
import java.util.List;

public interface SportTeamRepository extends JpaRepository<SportTeam, Long> {

	List<SportTeam> findByName(String name);
}
