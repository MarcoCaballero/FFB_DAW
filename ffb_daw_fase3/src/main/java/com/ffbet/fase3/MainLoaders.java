package com.ffbet.fase3;

import java.sql.Date;
import java.sql.Time;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.repositories.EgamesTeamRepository;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.Sports_match_repository;

@Component
public class MainLoaders {
	public static final int NUM_PER_TYPE = 15;
	@Autowired
	SportTeamRepository sporteamrepository;
	@Autowired
	EgamesTeamRepository egamesteamrepository;
	@Autowired
	Egames_match_repository egamesmatchrepository;
	@Autowired
	Sports_match_repository sportsmatchrepository;

	@PostConstruct
	private void initDatabase() {
		// 33rm 34fb ligas 19 38
		
		/*------------SPORT - TEAM------------*/
		
		SportTeam sportteam1 = new SportTeam("¡Ganar, Ganar y Ganar!", "Vicente Calderón", "Madrid", "Enrique Cerezo",
				10, 10, 0, "photo_team_cover1.png", "photo_team_logo1.png", "https://twitter.com/atleti?lang=es",
				"https://www.facebook.com/AtleticodeMadrid/", "https://plus.google.com/+atleticodemadrid",
				"Atlético de Madrid", "Fútbol", "Diego Simeone", "España");
		
		
		SportTeam sportteam2 =new SportTeam("¡Més que un club!", "Camp Nou", "Barcelona", "Jusep Marí Bartomeu", 34, 38,
				5, "photo_team_cover2.png", "photo_team_logo2.png", "https://twitter.com/fcbarcelona_es?lang=es",
				"https://www.facebook.com/fcbarcelona/", "https://plus.google.com/+FCBarcelona", "F.C. Barcelona",
				"Fútbol", "Luis Enrique", "España");
		
		
		SportTeam sportteam3 =new SportTeam("¡Así, Así, Así! Así gana el Madrid", "Santiago Bernabéu", "Madrid",
				"Florentino Pérez", 33, 19, 10, "photo_team_cover3.png", "photo_team_logo3.png",
				"https://twitter.com/realmadrid?lang=es", "https://www.facebook.com/RealMadrid/",
				"https://plus.google.com/+realmadrid", "Real Madrid C.F.", "Fútbol", "Zinedine Zidane", "España");
		
		sporteamrepository.save(sportteam1);

		sporteamrepository.save(sportteam2);

		sporteamrepository.save(sportteam3);
		
		SportTeam basketTeam1 = new SportTeam("Estadio1", "Presidente1", "BASKONIA", "Baloncesto", "Coach1");
		SportTeam basketTeam2 = new SportTeam("Estadio3", "Presidente3", "ESTUDIANTES", "Baloncesto", "Coach2");
		SportTeam basketTeam3 = new SportTeam("Estadio3", "Presidente3", "FUENLABRADA", "Baloncesto", "Coach3");
		
		sporteamrepository.save(basketTeam1);
		sporteamrepository.save(basketTeam2);
		sporteamrepository.save(basketTeam3);
		
		/*------------EGAMES - TEAM------------*/
		
		EgamesTeam egamesTeam1 = new EgamesTeam("SEÚL", "GRIETA", "SKT T1", "LOL", "KKOMA", "KOREA");
		EgamesTeam egamesTeam2 = new EgamesTeam("SEÚL", "SAMSUNG", "ROX", "LOL", "SHIN-HYUK", "KOREA");
		EgamesTeam egamesTeam3 = new EgamesTeam("L.A", "FAIRY", "CLOUD9", "LOL", "BOK HAN-GYU", "EEUU");
		
		egamesteamrepository.save(egamesTeam1);
		egamesteamrepository.save(egamesTeam2);
		egamesteamrepository.save(egamesTeam3);
		
		EgamesTeam csTeam1 = new EgamesTeam("SEÚL", "GRIETA", "SKT T1", "CS-GO", "KKOMA", "KOREA");
		EgamesTeam csTeam2 = new EgamesTeam("SEÚL", "SAMSUNG", "ROX", "CS-GO", "SHIN-HYUK", "KOREA");
		EgamesTeam csTeam3 = new EgamesTeam("L.A.", "FAIRY", "CLOUD9", "CS-GO", "BOK HANG-GIU", "EEUU");

		egamesteamrepository.save(csTeam1);
		egamesteamrepository.save(csTeam2);
		egamesteamrepository.save(csTeam3);
		
		/*------------SPORT - MATCH------------*/
		
		
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 1.17, 5.0, 20.0, sportteam2, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Baloncesto", 2.0, 2.0, 0.0, basketTeam1, basketTeam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Baloncesto", 2.22,1.53, 0.0, basketTeam2, basketTeam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Baloncesto", 1.38, 2.63, 0.0, basketTeam1, basketTeam3));
		
		/*------------EGAMES - MATCH------------*/
		
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "LOL", egamesTeam1, egamesTeam2, 0.71, 0.71, 4.0, 4.0));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "LOL", egamesTeam2, egamesTeam3, 4.0, 4.0, 0.71, 0.71));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "LOL", egamesTeam1, egamesTeam3, 3.3, 2.5, 5.0, 5.0));
		
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1, csTeam2, 0.71, 0.71, 4.0, 4.0));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1, csTeam3, 4.0, 4.0, 0.71, 0.71));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1, csTeam3, 3.3, 2.5, 5.0, 5.0));
	}

}
