package com.ffbet.fase3;

import java.sql.Date;
import java.sql.Time;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffbet.fase3.domain.EgamesMatch;
import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.SportsMatch;
import com.ffbet.fase3.repositories.EgamesTeamRepository;
import com.ffbet.fase3.repositories.Egames_match_repository;
import com.ffbet.fase3.repositories.PromotionRepository;
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
	@Autowired
	PromotionRepository promoRepository;

	@PostConstruct
	private void initDatabase() {
		// 33rm 34fb ligas 19 38
		
		/*------------SPORT - TEAM------------*/
		
		SportTeam sportteam1 = new SportTeam("¡Ganar, Ganar y Ganar!", "Vicente Calderón", "Madrid", "Enrique Cerezo",
				10, 10, 0, "photo_team_cover1.jpg", "photo_team_logo1.jpg", "https://twitter.com/atleti?lang=es",
				"https://www.facebook.com/AtleticodeMadrid/", "https://plus.google.com/+atleticodemadrid",
				"Atlético de Madrid", "Fútbol", "Diego Simeone", "España");
		
		
		SportTeam sportteam2 =new SportTeam("¡Més que un club!", "Camp Nou", "Barcelona", "Jusep Marí Bartomeu", 34, 38,
				5, "photo_team_cover2.jpg", "photo_team_logo2.jpg", "https://twitter.com/fcbarcelona_es?lang=es",
				"https://www.facebook.com/fcbarcelona/", "https://plus.google.com/+FCBarcelona", "F.C. Barcelona",
				"Fútbol", "Luis Enrique", "España");
		
		
		SportTeam sportteam3 =new SportTeam("¡Así, Así, Así! Así gana el Madrid", "Santiago Bernabéu", "Madrid",
				"Florentino Pérez", 33, 19, 10, "photo_team_cover3.jpg", "photo_team_logo3.jpg",
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
		
		EgamesTeam egamesTeam1 = new EgamesTeam("LOL", "GRIETA", "SKT T1", "KKOMA", "KOREA", "SEÚL");
		EgamesTeam egamesTeam2 = new EgamesTeam("LOL", "SAMSUNG", "ROX", "SHIN-HYUK", "KOREA", "SEÚL");
		EgamesTeam egamesTeam3 = new EgamesTeam("LOL", "FAIRY", "CLOUD9", "BOK HAN-GYU", "EEUU", "SEÚL");
		
		EgamesTeam egamesTeam4 = new EgamesTeam("LOL", "GRIETA", "SKT T1", "KKOma", "KOREA", "SEÚL"); 
		EgamesTeam egamesTeam5 = new EgamesTeam("LOL", "FAIRY", "CLOUD9", "BOK HANG-GYU", "EEUU", "L.A.");
		EgamesTeam egamesTeam6 = new EgamesTeam("LOL", "AGUILA", "ROJA", "KKOma", "THAY", "SEÚL"); 
		EgamesTeam egamesTeam7 = new EgamesTeam("LOL", "HALCON", "FURIOSO", "BOK HANG-GYU", "EEUU", "L.A.");
		
		EgamesTeam csTeam1 = new EgamesTeam("SEÚL", "GRIETA", "ENVYUS", "CS-GO", "KKOMA", "KOREA");
		EgamesTeam csTeam2 = new EgamesTeam("SEÚL", "SAMSUNG", "NA VI", "CS-GO", "SHIN-HYUK", "EEUU");
		EgamesTeam csTeam3 = new EgamesTeam("L.A.", "FAIRY", "VIRTUS PRO", "CS-GO", "BOK HANG-GIU", "EEUU");

		egamesteamrepository.save(csTeam1);
		egamesteamrepository.save(csTeam2);
		egamesteamrepository.save(csTeam3);
		
		egamesteamrepository.save(egamesTeam1);
		egamesteamrepository.save(egamesTeam2);
		egamesteamrepository.save(egamesTeam3);
		egamesteamrepository.save(egamesTeam4);
		egamesteamrepository.save(egamesTeam5);
		egamesteamrepository.save(egamesTeam6);
		egamesteamrepository.save(egamesTeam7);
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

		SportsMatch sp1 = new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 2.0, 2.0, 10.0, sportteam1, sportteam2);
		sp1.setFinished(true);
		sportsmatchrepository.save(sp1);
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 1.17, 5.0, 20.0, sportteam2, sportteam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 2.5, 2.5, 3.3, sportteam1, sportteam3));

		
		
		
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Baloncesto", 2.0, 2.0, 0.0, basketTeam1, basketTeam2));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Baloncesto", 2.22,1.53, 0.0, basketTeam2, basketTeam3));
		sportsmatchrepository.save(new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Baloncesto", 1.38, 2.63, 0.0, basketTeam1, basketTeam3));
		
		/*------------EGAMES - MATCH------------*/

													/*LOL*/
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "LOL", 2.0, 2.0, 10.0, "SKT","SKT", 10.0, egamesTeam1, egamesTeam2));
		
													/*CS-GO*/
		
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:01"), "CS-GO", 2.0, 2.0, 10.0, "FURIOSO","DÉ", 10.0, egamesTeam3, egamesTeam4));
		
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "LOL", egamesTeam1, egamesTeam2, 0.71, 0.71, 4.0, 4.0));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "LOL", egamesTeam2, egamesTeam3, 4.0, 4.0, 0.71, 0.71));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "LOL", egamesTeam1, egamesTeam3, 3.3, 2.5, 5.0, 5.0));
		
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1, csTeam2, 0.71, 0.71, 4.0, 4.0));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1, csTeam3, 4.0, 4.0, 0.71, 0.71));
		egamesmatchrepository.save(new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1, csTeam3, 3.3, 2.5, 5.0, 5.0));

		/* PROMOTIONS */
		
		promoRepository.save(new Promotion("BONODESCUENTO", "SUPER PROMO", "Clica en Código y consigue el código promocional para obetener un 5% de descuento en tu próxima apuesta", "fgs4trfs", 5, "photo_promo1.jpg"));
		promoRepository.save(new Promotion("PROMOCIONREGALO", "REGALO DE 5€", "¡Te regalamos 5€ en crédito para apostar en FFBet!", "hghd55434gfsg", 5, "photo_promo2.jpg"));
		promoRepository.save(new Promotion("BONODESCUENTO", "MAXI DESCUENTO", "Sé el primero en aprovecharte de esta increíble promoción del 25% de descuento en tu próxima apuesta", "gsrgr35ghdd", 25, "photo_promo4.jpg"));
		promoRepository.save(new Promotion("PROMOCIONREGALO", "NOS SENTIMOS GENEROSOS", "Con esta promoción podrás conseguir nada menos que ¡8€ de regalo! en tu crédito de FFbet", "sbsgse43556gfds", 8,"photo_promo5.jpg"));
		//promoRepository.save(new Promotion("BONODESCUENTO", "ESTÁS DE SUERTE", "Apuesta ya usando esta promoción que te dará 20%", "ryui57yghrjdks", 20,"photo_promo3.jpg"));
	}

}
