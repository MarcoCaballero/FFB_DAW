package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ffbet.fase3.domain.Sport_team;
import java.lang.String;
import java.util.List;

public interface Sport_team_repository extends JpaRepository<Sport_team, Long> {

	List<Sport_team> findByName(String name);
}
