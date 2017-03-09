package com.ffbet.fase3.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.EgamesTeamRepository;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.Sports_match_repository;
import com.ffbet.fase3.security.UserAuthComponent;

@RestController
public class ImageTestController {



	@Autowired
	private SportTeamRepository sport_team_repo;
	@Autowired
	private EgamesTeamRepository egame_team_repo;
	@Autowired
	private Sports_match_repository sport_match_repo;
	@Autowired
	private Egames_match_repository egames_match_repo;
	@Autowired
	UserAuthComponent userComp;
	/**
	 * Initializer on PostConstruct
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@PostConstruct
	public void init() throws IOException, ParseException {

		SportTeam sport_team = new SportTeam();
		sport_team.setName("P_001");
		sport_team.setCity("Madrid");
		sport_team.setCoach("marianete");

		SportTeam sp1 = new SportTeam();
		sp1.setName("Equipo 1");
		sp1.setCoach("Peperoni");

		SportTeam sp2 = new SportTeam();
		sp2.setName("Equipo 2");
		sp2.setCoach("Queseroni");

		SportsMatch sportMatch1 = new SportsMatch();
		sportMatch1.setHomeTeam("RealMadrid");
		sportMatch1.setVisitingTeam("Atletico");
		sportMatch1.setType("Futbol");

		SportsMatch sportMatch2 = new SportsMatch();
		sportMatch2.setHomeTeam("Fuenla");
		sportMatch2.setVisitingTeam("Alcorcon");
		sportMatch2.setType("Futbol");

		SportsMatch sportMatch3 = new SportsMatch();
		sportMatch3.setHomeTeam("RealMadrid");
		sportMatch3.setVisitingTeam("Atletico");
		sportMatch3.setType("Baloncesto");

		SportsMatch sportMatch4 = new SportsMatch();
		sportMatch4.setHomeTeam("Barcelona");
		sportMatch4.setVisitingTeam("Atletico");
		sportMatch4.setType("Baloncesto");

		EgamesMatch egamesMatch1 = new EgamesMatch();
		egamesMatch1.setHomeTeam("asd");
		egamesMatch1.setVisitingTeam("asdddd");
		egamesMatch1.setType("Lol");
		egamesMatch1.setWinHome(true);
		egamesMatch1.setFirstBloodVisiting(true);

		EgamesMatch egamesMatch2 = new EgamesMatch();
		egamesMatch2.setHomeTeam("SKT");
		egamesMatch2.setVisitingTeam("Origin");
		egamesMatch2.setType("Csgo");
		egamesMatch2.setWinHome(true);
		egamesMatch2.setFirstBloodVisiting(true);

		egames_match_repo.save(egamesMatch2);
		egames_match_repo.save(egamesMatch1);
		sport_match_repo.save(sportMatch1);
		sport_match_repo.save(sportMatch2);
		sport_match_repo.save(sportMatch3);
		sport_match_repo.save(sportMatch4);

		EgamesTeam eg_team = new EgamesTeam();
		eg_team.setName("P_002");
		eg_team.setCity("Valencia");
		eg_team.setCoach("marianeta");
		
		egame_team_repo.save(eg_team);
		sport_team_repo.save(sport_team);
		sport_team_repo.save(sp1);
		sport_team_repo.save(sp2);



	}


	

	

}
