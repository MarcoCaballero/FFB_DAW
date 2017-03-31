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
		
		/*------------FOOTBALL - TEAM------------*/

		SportTeam sportTeam1 = new SportTeam("¡Ganar, Ganar y Ganar!", "Vicente Calderón", "Madrid", "Enrique Cerezo",
				10, 10, 0, "photo_team_cover1.jpg", "photo_team_logo1.jpg", "https://twitter.com/atleti?lang=es",
				"https://www.facebook.com/AtleticodeMadrid/", "https://plus.google.com/+atleticodemadrid",
				"Atlético de Madrid", "Fútbol", "Diego Simeone", "España");

		SportTeam sportTeam2 = new SportTeam("¡Més que un club!", "Camp Nou", "Barcelona", "Jusep Marí Bartomeu", 34,
				38, 5, "photo_team_cover2.jpg", "photo_team_logo2.jpg", "https://twitter.com/fcbarcelona_es?lang=es",
				"https://www.facebook.com/fcbarcelona/", "https://plus.google.com/+FCBarcelona", "F.C. Barcelona",
				"Fútbol", "Luis Enrique", "España");

		SportTeam sportTeam3 = new SportTeam("¡Así, Así, Así! Así gana el Madrid", "Santiago Bernabéu", "Madrid",
				"Florentino Pérez", 33, 19, 10, "photo_team_cover3.jpg", "photo_team_logo3.jpg",
				"https://twitter.com/realmadrid?lang=es", "https://www.facebook.com/RealMadrid/",
				"https://plus.google.com/+realmadrid", "Real Madrid C.F.", "Fútbol", "Zinedine Zidane", "España");

		SportTeam sportTeam4 = new SportTeam("O ganamos o perdemos", "Mestalla", "Valencia", "Layhoon Chan", 12, 8, 7,
				"photo_team_cover4.jpg", "photo_team_logo4.jpg", "https://twitter.com/valenciacf?lang=es",
				"https://es-la.facebook.com/ValenciaCF/", "https://plus.google.com/+valenciacf", "Valencia C.F.",
				"Fútbol", "Voro", "España");

		SportTeam sportTeam5 = new SportTeam("Somos reales", "José Zorrilla", "Valladolid", "Layhoon Chan", 5, 2, 0,
				"photo_team_cover5.jpg", "photo_team_logo5.jpg", "https://twitter.com/realvalladolid?lang=es",
				"https://es-es.facebook.com/RealValladolid/", "https://plus.google.com/+realvalladolid",
				"Real Valladolid C.F.", "Fútbol", "Paco Herrera", "España");

		SportTeam sportTeam6 = new SportTeam("Somos reales", "San Mamés", "Bilbao", "Josu Urrutia", 6, 3, 2,
				"photo_team_cover6.jpg", "photo_team_logo6.jpg", "https://twitter.com/athleticclub?lang=es",
				"https://es-es.facebook.com/ATHLETICCLUB/", "https://plus.google.com/+athletic", "Athletic Club",
				"Fútbol", "Ernesto Valverde", "España");

		sporteamrepository.save(sportTeam1);
		sporteamrepository.save(sportTeam2);
		sporteamrepository.save(sportTeam3);
		sporteamrepository.save(sportTeam4);
		sporteamrepository.save(sportTeam5);
		sporteamrepository.save(sportTeam6);

		/*------------BASKET - TEAM------------*/

		SportTeam basketTeam1 = new SportTeam("Estadio1", "Presidente1", "BASKONIA", "Baloncesto", "Coach1");
		SportTeam basketTeam2 = new SportTeam("Estadio2", "Presidente2", "ESTUDIANTES", "Baloncesto", "Coach2");
		SportTeam basketTeam3 = new SportTeam("Estadio3", "Presidente3", "FUENLABRADA", "Baloncesto", "Coach3");

		sporteamrepository.save(basketTeam1);
		sporteamrepository.save(basketTeam2);
		sporteamrepository.save(basketTeam3);

		/*------------LOL - TEAM------------*/

		EgamesTeam egamesTeam1 = new EgamesTeam("KKOMA", "GRIETA", "SKT T1", "LOL", "KOREA", "SEÚL");
		EgamesTeam egamesTeam2 = new EgamesTeam("SHIN-HYUK", "SAMSUNG", "ROX", "LOL", "KOREA", "SEÚL");
		EgamesTeam egamesTeam3 = new EgamesTeam("BOK HAN-GYU", "FAIRY", "CLOUD9", "LOL", "EEUU", "SEÚL");
		EgamesTeam egamesTeam4 = new EgamesTeam("JOSÉ LUIS", "GRIETA", "ORIGEN", "LOL", "ESPAÑA", "MURCIA");
		EgamesTeam egamesTeam5 = new EgamesTeam("JOHN PEARL", "FAIRY", "CLOUD9", "LOL", "EEUU", "L.A.");
		EgamesTeam egamesTeam6 = new EgamesTeam("KKOMA", "AGUILA", "FANATIC", "LOL", "THAY", "SEÚL");
		EgamesTeam egamesTeam7 = new EgamesTeam("SAMUEL L. JACKSON", "HALCON", "TSM", "LOL", "EEUU", "L.A.");

		egamesteamrepository.save(egamesTeam1);
		egamesteamrepository.save(egamesTeam2);
		egamesteamrepository.save(egamesTeam3);
		egamesteamrepository.save(egamesTeam4);
		egamesteamrepository.save(egamesTeam5);
		egamesteamrepository.save(egamesTeam6);
		egamesteamrepository.save(egamesTeam7);

		/*------------CS-GO - TEAM------------*/

		EgamesTeam csTeam1 = new EgamesTeam("SEÚL", "GRIETA", "ENVYUS", "CS-GO", "KKOMA", "KOREA");
		EgamesTeam csTeam2 = new EgamesTeam("SEÚL", "SAMSUNG", "NA VI", "CS-GO", "SHIN-HYUK", "EEUU");
		EgamesTeam csTeam3 = new EgamesTeam("L.A.", "FAIRY", "VIRTUS PRO", "CS-GO", "BOK HANG-GIU", "EEUU");

		egamesteamrepository.save(csTeam1);
		egamesteamrepository.save(csTeam2);
		egamesteamrepository.save(csTeam3);

		/*------------SPORT - MATCH------------*/

		/* FOOTBALL */

		SportsMatch fM1 = new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "Fútbol", 2.0, 2.0,
				10.0, sportTeam1, sportTeam2);
		fM1.setFinished(true);
		SportsMatch fM2 = new SportsMatch(Date.valueOf("2017-04-15"), Time.valueOf("19:30:00"), "Fútbol", 1.17, 5.0,
				20.0, sportTeam2, sportTeam3);
		fM2.setFinished(true);
		SportsMatch fM3 = new SportsMatch(Date.valueOf("2017-03-05"), Time.valueOf("18:00:00"), "Fútbol", 2.5, 2.5, 3.3,
				sportTeam3, sportTeam1);
		fM3.setFinished(true);
		SportsMatch fM4 = new SportsMatch(Date.valueOf("2017-02-28"), Time.valueOf("22:00:00"), "Fútbol", 5.0, 20.0,
				1.17, sportTeam1, sportTeam4);
		fM4.setFinished(true);
		SportsMatch fM5 = new SportsMatch(Date.valueOf("2017-08-20"), Time.valueOf("22:00:05"), "Fútbol", 2.5, 2.5, 3.3,
				sportTeam5, sportTeam3);
		fM5.setFinished(true);
		SportsMatch fM6 = new SportsMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5, 3.3,
				sportTeam1, sportTeam5);
		fM6.setFinished(true);
		SportsMatch fM7 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5, 3.3,
				sportTeam1, sportTeam5);
		fM7.setFinished(true);
		SportsMatch fM8 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5, 3.3,
				sportTeam1, sportTeam6);
		fM8.setFinished(true);
		SportsMatch fM9 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5, 3.3,
				sportTeam2, sportTeam6);
		fM9.setFinished(true);
		SportsMatch fM10 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5,
				3.3, sportTeam6, sportTeam5);
		fM10.setFinished(true);
		SportsMatch fM11 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5,
				3.3, sportTeam5, sportTeam2);
		fM11.setFinished(true);
		SportsMatch fM12 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 2.5, 2.5,
				3.3, sportTeam4, sportTeam1);
		SportsMatch fM13 = new SportsMatch(Date.valueOf("2017-03-21"), Time.valueOf("22:00:15"), "Fútbol", 1.17, 5.0,
				2.0, sportTeam3, sportTeam6);

		sportsmatchrepository.save(fM1);
		sportsmatchrepository.save(fM2);
		sportsmatchrepository.save(fM3);
		sportsmatchrepository.save(fM4);
		sportsmatchrepository.save(fM5);
		sportsmatchrepository.save(fM6);
		sportsmatchrepository.save(fM7);
		sportsmatchrepository.save(fM8);
		sportsmatchrepository.save(fM9);
		sportsmatchrepository.save(fM10);
		sportsmatchrepository.save(fM11);
		sportsmatchrepository.save(fM12);
		sportsmatchrepository.save(fM13);

		/* BASKETBALL */

		SportsMatch bM1 = new SportsMatch(Date.valueOf("2017-05-30"), Time.valueOf("22:00:00"), "Baloncesto", 2.0, 2.0,
				0.0, basketTeam1, basketTeam2);
		bM1.setFinished(true);
		SportsMatch bM2 = new SportsMatch(Date.valueOf("2017-11-09"), Time.valueOf("20:30:00"), "Baloncesto", 2.22,
				1.53, 0.0, basketTeam2, basketTeam3);
		SportsMatch bM3 = new SportsMatch(Date.valueOf("2017-12-24"), Time.valueOf("19:45:00"), "Baloncesto", 1.38,
				2.63, 0.0, basketTeam3, basketTeam1);

		sportsmatchrepository.save(bM1);
		sportsmatchrepository.save(bM2);
		sportsmatchrepository.save(bM3);

		/*------------EGAMES - MATCH------------*/

		/* LOL */

		EgamesMatch lM1 = new EgamesMatch(Date.valueOf("2017-05-05"), Time.valueOf("22:30:00"), "LOL", egamesTeam1,
				egamesTeam2, 2.0, 2.0, 10.0, 10.0);
		lM1.setFinished(true);
		EgamesMatch lM2 = new EgamesMatch(Date.valueOf("2017-01-22"), Time.valueOf("21:15:00"), "LOL", egamesTeam3,
				egamesTeam2, 2.0, 2.0, 10.0, 10.0);
		lM2.setFinished(true);
		EgamesMatch lM3 = new EgamesMatch(Date.valueOf("2017-09-18"), Time.valueOf("16:20:00"), "LOL", egamesTeam3,
				egamesTeam1, 2.0, 2.0, 10.0, 10.0);

		egamesmatchrepository.save(lM1);
		egamesmatchrepository.save(lM2);
		egamesmatchrepository.save(lM3);

		/* CS-GO */

		EgamesMatch cM1 = new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1,
				csTeam2, 0.71, 0.71, 4.0, 4.0);
		cM1.setFinished(true);
		EgamesMatch cM2 = new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1,
				csTeam3, 4.0, 4.0, 0.71, 0.71);
		EgamesMatch cM3 = new EgamesMatch(Date.valueOf("2017-03-20"), Time.valueOf("22:00:00"), "CS-GO", csTeam1,
				csTeam3, 3.3, 2.5, 5.0, 5.0);

		egamesmatchrepository.save(cM1);
		egamesmatchrepository.save(cM2);
		egamesmatchrepository.save(cM3);

		/* PROMOTIONS */

		promoRepository.save(new Promotion("BONODESCUENTO", "SUPER PROMO",
				"Clica en Código y consigue el código promocional para obetener un 5% de descuento en tu próxima apuesta",
				"fgs4trfs", 5, "photo_promo1.jpg"));
		promoRepository.save(new Promotion("PROMOCIONREGALO", "REGALO DE 5€",
				"¡Te regalamos 5€ en crédito para apostar en FFBet!", "hghd55434gfsg", 5, "photo_promo2.jpg"));
		promoRepository.save(new Promotion("BONODESCUENTO", "MAXI DESCUENTO",
				"Sé el primero en aprovecharte de esta increíble promoción del 25% de descuento en tu próxima apuesta",
				"gsrgr35ghdd", 25, "photo_promo4.jpg"));
		promoRepository.save(new Promotion("PROMOCIONREGALO", "NOS SENTIMOS GENEROSOS",
				"Con esta promoción podrás conseguir nada menos que ¡8€ de regalo! en tu crédito de FFbet",
				"sbsgse43556gfds", 8, "photo_promo5.jpg"));
		// promoRepository.save(new Promotion("BONODESCUENTO", "ESTÁS DE
		// SUERTE", "Apuesta ya usando esta promoción que te dará 20%",
		// "ryui57yghrjdks", 20,"photo_promo3.jpg"));
	}

}
