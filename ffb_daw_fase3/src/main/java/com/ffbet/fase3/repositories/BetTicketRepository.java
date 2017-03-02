package com.ffbet.fase3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.BetTicket;

public interface BetTicketRepository extends JpaRepository<BetTicket, Long> {

}
