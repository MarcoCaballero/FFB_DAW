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
		SportTeam sportteam1 = new SportTeam("¡Ganar, Ganar y Ganar!", "Vicente Calderón", "Fútbol", "Enrique Cerezo",
				10, 10, 0, "photo_team_cover1.png", "photo_team_logo1.png", "https://twitter.com/atleti?lang=es",
				"https://www.facebook.com/AtleticodeMadrid/", "https://plus.google.com/+atleticodemadrid",
				"Atlético de Madrid", "Diego Simeone", "España", "Madrid");
		
		
		SportTeam sportteam2 =new SportTeam("¡Més que un club!", "Camp Nou", "Fútbol", "Jusep Marí Bartomeu", 34, 38,
				5, "photo_team_cover3.png", "photo_team_logo3.png", "https://twitter.com/fcbarcelona_es?lang=es",
				"https://www.facebook.com/fcbarcelona/", "https://plus.google.com/+FCBarcelona", "F.C. Barcelona",
				"Luis Enrique", "España", "Barcelona");
		
		
		SportTeam sportteam3 =new SportTeam("¡Así, Así, Así! Así gana el Madrid", "Santiago Bernabéu", "Fútbol",
				"Florentino Pérez", 33, 19, 10, "photo_team_cover3.png", "photo_team_logo3.png",
				"https://twitter.com/realmadrid?lang=es", "https://www.facebook.com/RealMadrid/",
				"https://plus.google.com/+realmadrid", "Real Madrid C.F.", "Zinedine Zidane", "España", "Madrid");
		
		
		
		
		sporteamrepository.save(sportteam1);

		sporteamrepository.save(sportteam2);

		sporteamrepository.save(sportteam3);

		sporteamrepository.save(new SportTeam("Estadio1", "Presidente1", "BASKONIA", "Coach1", "Baloncesto"));
		sporteamrepository.save(new SportTeam("Estadio3", "Presidente3", "ESTUDIANTES", "Coach3", "Baloncesto"));
		sporteamrepository.save(new SportTeam("Estadio3", "Presidente3", "FUENLABRADA", "Coach3", "Baloncesto"));
		
		/*------------EGAMES - TEAM------------*/
		
		egamesteamrepository.save(new EgamesTeam("LOL", "GRIETA", "SKT T1", "KKOma", "KOREA", "SEÚL"));
		egamesteamrepository.save(new EgamesTeam("LOL", "SAMSUNG", "ROX", "SHIN-HYUK", "KOREA", "SEÚL"));
		egamesteamrepository.save(new EgamesTeam("LOL", "FAIRY", "CLOUD9", "BOK HANG-GYU", "EEUU", "L.A."));

		egamesteamrepository.save(new EgamesTeam("CS-GO", "GRIETA", "SKT T1", "KKOma", "KOREA", "SEÚL"));
		egamesteamrepository.save(new EgamesTeam("CS-GO", "SAMSUNG", "ROX", "SHIN-HYUK", "KOREA", "SEÚL"));
		egamesteamrepository.save(new EgamesTeam("CS-GO", "FAIRY", "CLOUD9", "BOK HANG-GIU", "EEUU", "L.A."));
		
		EgamesTeam egamesteam1 = new EgamesTeam("LOL", "GRIETA", "SKT T1", "KKOma", "KOREA", "SEÚL"); 
		EgamesTeam egamesteam2 = new EgamesTeam("LOL", "FAIRY", "CLOUD9", "BOK HANG-GYU", "EEUU", "L.A.");
		EgamesTeam egamesteam3 = new EgamesTeam("CS-GO", "AGUILA", "ROJA", "KKOma", "THAY", "SEÚL"); 
		EgamesTeam egamesteam4 = new EgamesTeam("CS-GO", "HALCON", "FURIOSO", "BOK HANG-GYU", "EEUU", "L.A.");
		
		egamesteamrepository.save(egamesteam1);
		egamesteamrepository.save(egamesteam2);
		egamesteamrepository.save(egamesteam3);
		egamesteamrepository.save(egamesteam4);
		/*------------SPORT - MATCH------------*/
		
												/*FOOTBALL*/
		
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Fútbol", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:02"), "Fútbol", 1.17, 5.0, 20.0, sportteam2, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:03"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:04"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:05"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:06"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:07"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:08"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:09"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:10"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:11"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:22"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:33"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:44"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:55"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:66"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:77"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:88"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:99"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:12"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:13"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:14"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));
		
												/*BASKETBALL*/
		
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "Baloncesto", 2.0, 2.0, 10.0, sportteam1, sportteam2));
		
		/*------------EGAMES - MATCH------------*/
													/*LOL*/
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesteam1, egamesteam2));
		
													/*CS-GO*/
		
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesteam3, egamesteam4));
		
	}

}
