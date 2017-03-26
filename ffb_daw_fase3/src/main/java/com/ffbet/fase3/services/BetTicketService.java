package com.ffbet.fase3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.repositories.BetTicketRepository;

@Service
public class BetTicketService {
	
	@Autowired
	BetTicketRepository betTicketRepo;
	
	public List<BetTicket> findAll(){
		return betTicketRepo.findAll();
	}
	
	public BetTicket findOne(long id){
		return betTicketRepo.findOne(id);
	}
	
	public List<BetTicket> findByFinished(){
		return betTicketRepo.findByFinished();
	}

}
