package com.ffbet.fase3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ffbet.fase3.domain.BetTicket;

public interface BetTicketRepository extends JpaRepository<BetTicket, Long> {

	@Query("SELECT u FROM BetTicket u where u.isFinished = true")
	List<BetTicket> findByFinished();
}
