package com.ffbet.fase3.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.repositories.EgamesTeamRepository;
import com.ffbet.fase3.repositories.SportTeamRepository;

@RestController
public class ImageTestController {

	@Autowired
	private SportTeamRepository sport_team_repo;
	@Autowired
	private EgamesTeamRepository egame_team_repo;

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

		EgamesTeam eg_team = new EgamesTeam();
		eg_team.setName("P_002");
		eg_team.setCity("Valencia");
		eg_team.setCoach("marianeta");
		/*
		 * try { FileInputStream f_in = new
		 * FileInputStream("C:\\Users\\Marco\\Desktop\\gato.jpg");
		 * 
		 * sport_team.setShield_image(IOUtils.toByteArray(f_in)); } catch
		 * (Exception e) { // TODO: handle exception e.printStackTrace(); }
		 */
		egame_team_repo.save(eg_team);
		sport_team_repo.save(sport_team);
		uploadImageShield("C:\\Users\\Marco\\Desktop\\gato.jpg", "P_001", 0);

	}

	/**
	 * Testing a way to get and show the 'shield_image' from database to
	 * browser.
	 * 
	 * @param response
	 * @param name
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("/images/{name}")
	// Forma eficiente para devolver binario
	public void getAndshowImage(HttpServletResponse response, @PathVariable String name)
			throws FileNotFoundException, IOException {
		response.addHeader("Content/type", "image/jpeg");

		SportTeam sport_team = sport_team_repo.findByName(name).get(0);// -->
																		// find
																		// next
																		// toSelect
		byte[] imageByte = sport_team.getLogo_image();
		try {
			IOUtils.write(imageByte, response.getOutputStream());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * Testing a way to upload the 'shield_image' to database.
	 * 
	 * @param path,
	 *            path to the image file (or aany file)
	 * @param name,
	 *            the {@link SportTeam} name
	 * @param toSelect,
	 *            strategy to select: 0 - to select the first occurrence, 1 - to
	 *            select the last occurrence.
	 * 
	 */
	public void uploadImageShield(String path, String name, int toSelect) {
		SportTeam s_t = sport_team_repo.findByName(name).get(0);// --> toSelect
		try {
			FileInputStream f_in = new FileInputStream(path);

			s_t.setLogo_image(IOUtils.toByteArray(f_in));
			;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		sport_team_repo.save(s_t);
	}

}