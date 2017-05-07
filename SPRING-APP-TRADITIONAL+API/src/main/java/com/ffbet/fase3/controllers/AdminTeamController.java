package com.ffbet.fase3.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.TeamService;
import com.ffbet.fase3.services.UserService;

/**
 * Controller class {@link AdminTeamController} provides methods to map the
 * URL's that reference to the {@link Team} Entity. This controller also extends
 * to an Abstract class {@link RedirectController} that provides methods common
 * to several controllers
 *
 *
 * @see {@link Team}, {@link RedirectController}
 * @author Marco
 * @version 1.0
 */
@Controller
public class AdminTeamController extends RedirectController {

	String template = TemplatesPath.ADMIN_TEAM.toString();
	String redirect = "redirect:/admin-teams/";

	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;

	@Autowired
	UserAuthComponent userComp;

	private boolean noFailsNewSport = false;
	private boolean noFailsNewEgames = false;

	private boolean photoLogoError = false;
	private boolean photoStadiumError = false;

	/* ADMIN */

	/**
	 * Method {@linkplain getTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = { "/admin-teams", "/admin-teams/" })
	public String getTeamTemplate(HttpServletRequest request, Model model) {

		if (userComp.isLoggedUser()) {
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		} else {
			return "redirect:/logOut";
		}

		model.addAttribute("SportsTeams", teamService.findAllSportsTeams());
		model.addAttribute("EgamesTeams", teamService.findAllEgamesTeams());

		model.addAttribute("noBadNewSport", noFailsNewSport);
		model.addAttribute("noBadNewEgames", noFailsNewEgames);

		model.addAttribute("showsLogoError", photoLogoError);
		model.addAttribute("showsStadiumError", photoStadiumError);

		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, template);
		return response;

	}

	/**
	 * Method {@linkplain addTeam()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the new team ADDED.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping(value = { "/admin-teams/newSportsTeam" })
	public String addSportsTeam(@RequestParam("sportsType") String type, @RequestParam("sportsName") String name,
			@RequestParam("sportsCountry") String country, @RequestParam("sportsCity") String city,
			@RequestParam("stadium") String stadium, @RequestParam("slogan") String slogan,
			@RequestParam("president") String president, @RequestParam("sportsCoach") String coach,
			@RequestParam("leaguesWeb") String leagues, @RequestParam("cupsWeb") String cups,
			@RequestParam("championsWeb") String champions, @RequestParam("facebook_Uri") String fb,
			@RequestParam("twitter_Uri") String tw, @RequestParam("google_Uri") String go,
			@RequestParam("logoImage") MultipartFile logoImg, @RequestParam("stadiumImage") MultipartFile stadiumImg) {

		String fileNameLogo;
		String fileNameStadium;

		try {
			SportTeam team = new SportTeam();

			team.setType(type);
			team.setName(name);
			team.setCountry(country);
			team.setCity(city);
			team.setStadium(stadium);
			team.setSlogan(slogan);
			team.setPresident(president);
			team.setCoach(coach);
			team.setLeagues(Integer.parseInt(leagues));
			team.setCups(Integer.parseInt(cups));
			team.setChampions(Integer.parseInt(champions));
			team.setFacebook_Uri(fb);
			team.setTwitter_Uri(tw);
			team.setGoogle_Uri(go);

			teamService.saveSportTeam(team);

			team = teamService.findByNameSport(name);

			if (!logoImg.isEmpty()) {
				fileNameLogo = userService.handleUploadImagetoDatabase(logoImg, team.getId(),
						FilesPath.FILES_TEAMS_LOGO.toString());
				if (fileNameLogo.equals("ERROR")) {
					photoLogoError = true;
				} else {
					photoLogoError = false;

					team.setLogo_image(fileNameLogo);
				}
			}

			if (!stadiumImg.isEmpty()) {
				fileNameStadium = userService.handleUploadImagetoDatabase(stadiumImg, team.getId(),
						FilesPath.FILES_TEAMS_COVER.toString());
				if (fileNameStadium.equals("ERROR")) {
					photoStadiumError = true;
				} else {
					photoStadiumError = false;

					team.setStadium_image(fileNameStadium);
				}
			}

			// team.setStadium_image(sImage);
			// team.setLogo_image(lImage);

			teamService.saveSportTeam(team);

			noFailsNewSport = false;
		} catch (Exception e) {
			noFailsNewSport = true;
		}

		return redirect;

	}

	@RequestMapping("/images/logos/{fileName}")
	public void handleAvatarsFileLogo(Model model, HttpServletResponse response, @PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		InputStream file = userService.handleFileDownload(response, fileName, "logos");

		if (file != null) {
			res.setContentType("image/jpeg");

			FileCopyUtils.copy(file, response.getOutputStream());

		} else {
			res.sendError(404, "File" + fileName + "does not exist");
		}

	}

	public void handleAvatarsFileStadium(Model model, HttpServletResponse response, @PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		InputStream file = userService.handleFileDownload(response, fileName, "covers");

		if (file != null) {
			res.setContentType("image/jpeg");

			FileCopyUtils.copy(file, response.getOutputStream());

		} else {
			res.sendError(404, "File" + fileName + "does not exist");
		}
	}

	/**
	 * Method {@linkplain addTeam()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the new team ADDED.
	 *
	 * @return
	 */
	@PostMapping(value = { "/admin-teams/newEgamesTeam" })
	public String addEgamesTeam(EgamesTeam team, @RequestParam("egamesType") String type,
			@RequestParam("egamesName") String name, @RequestParam("egamesCountry") String country,
			@RequestParam("egamesCity") String city, @RequestParam("egamesCoach") String coach,
			@RequestParam("sponsor") String sponsor) {

		if (!name.isEmpty() && !country.isEmpty() && !city.isEmpty() && !coach.isEmpty() && !sponsor.isEmpty()) {
			team.setType(type);
			team.setName(name);
			team.setCountry(country);
			team.setCity(city);
			team.setCoach(coach);
			team.setSponsor(sponsor);

			teamService.saveEgamesTeam(team);

			noFailsNewEgames = false;

			System.out.println("Nombre: " + name);
		} else {
			noFailsNewEgames = true;
		}

		return redirect;

	}

	/**
	 * Method {@linkplain updateTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the updated team [selected by id].
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin-teams/updateSports/{id}")
	public String updateSportsTeam(@PathVariable Long id, @RequestParam("sportsUpName") String name,
			@RequestParam("sportsUpCoach") String coach, @RequestParam("sportsUpCountry") String country,
			@RequestParam("sportsUpCity") String city, @RequestParam("sportsUpSlogan") String slogan,
			@RequestParam("sportsUpStadium") String stadium, @RequestParam("sportsUpPresident") String president,
			@RequestParam("sportsUpLeagues") String leagues, @RequestParam("sportsUpCups") String cups,
			@RequestParam("sportsUpChampions") String champions, @RequestParam("sportsUpFb") String fb,
			@RequestParam("sportsUpTw") String tw, @RequestParam("sportsUpGo") String go) {

		SportTeam team = teamService.findOneSportTeam(id);

		try {
			if (!name.isEmpty()) {
				team.setName(name);
			}
			if (!coach.isEmpty()) {
				team.setCoach(coach);
			}
			if (!country.isEmpty()) {
				team.setCountry(country);
			}
			if (!city.isEmpty()) {
				team.setCity(city);
			}
			if (!slogan.isEmpty()) {
				team.setSlogan(slogan);
			}
			if (!slogan.isEmpty()) {
				team.setSlogan(slogan);
			}
			if (!president.isEmpty()) {
				team.setPresident(president);
			}
			if (!leagues.isEmpty()) {
				team.setLeagues(Integer.parseInt(leagues));
			}
			if (!cups.isEmpty()) {
				team.setCups(Integer.parseInt(cups));
			}
			if (!champions.isEmpty()) {
				team.setChampions(Integer.parseInt(champions));
			}
			if (!fb.isEmpty()) {
				team.setFacebook_Uri(fb);
			}
			if (!tw.isEmpty()) {
				team.setTwitter_Uri(tw);
			}
			if (!go.isEmpty()) {
				team.setGoogle_Uri(go);
			}

			teamService.saveSportTeam(team);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return redirect;
	}

	/**
	 * Method {@linkplain updateTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser with the updated team [selected by id].
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin-teams/updateEgames/{id}")
	public String updateEgamesTeam(@PathVariable Long id, @RequestParam("egamesUpName") String name,
			@RequestParam("egamesUpCoach") String coach, @RequestParam("egamesUpCountry") String country,
			@RequestParam("egamesUpCity") String city) {

		EgamesTeam team = teamService.findOneEgamesTeam(id);

		try {
			if (!name.isEmpty()) {
				team.setName(name);
			}
			if (!coach.isEmpty()) {
				team.setCoach(coach);
			}
			if (!country.isEmpty()) {
				team.setCountry(country);
			}
			if (!city.isEmpty()) {
				team.setCity(city);
			}

			teamService.saveEgamesTeam(team);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return redirect;
	}

	/**
	 * Method {@linkplain deleteTeamByID()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows it through the browser without hte deleted team [ById].
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/admin-teams/delete/{id}")
	public String deleteTeamByID(@PathVariable("id") long id) {

		teamService.delete(id);

		return redirect;

	}

}
