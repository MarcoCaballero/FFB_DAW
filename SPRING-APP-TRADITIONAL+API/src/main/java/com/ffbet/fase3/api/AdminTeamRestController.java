package com.ffbet.fase3.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.SportTeam;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.services.TeamService;
import com.ffbet.fase3.services.UserService;

@RestController
@RequestMapping("/api/teams")
public class AdminTeamRestController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private UserService userService;

	// mostrar equipos
	@GetMapping
	public List<Team> getTeams() {
		return teamService.findAllTeams();
	}

	@GetMapping("/sports")
	public List<SportTeam> getSportTeams() {
		return teamService.findAllSportsTeams();
	}

	@GetMapping("/egames")
	public List<EgamesTeam> getEgamesTeams() {
		return teamService.findAllEgamesTeams();
	}

	@GetMapping("/sports/{id}")
	public ResponseEntity<Team> getSportTeamById(@PathVariable long id) {
		Team team = teamService.findOneSportTeam(id);
		if (team != null) {
			return new ResponseEntity<>(team, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// añadir equipos
	@PostMapping("/sports")
	@ResponseStatus(HttpStatus.CREATED)
	public SportTeam newSportTeam(@RequestBody SportTeam sportTeam) {

		teamService.saveSportTeam(sportTeam);

		return sportTeam;
	}

	@PostMapping("/egames")
	@ResponseStatus(HttpStatus.CREATED)
	public EgamesTeam newEgamesTeam(@RequestBody EgamesTeam egamesTeam) {

		teamService.saveEgamesTeam(egamesTeam);

		return egamesTeam;
	}

	@PutMapping("/uploadImages/{id}")
	public ResponseEntity<SportTeam> handleFileUpload(@RequestParam("fileLogo") MultipartFile fileLogo,
			@RequestParam("fileStadium") MultipartFile fileStadium, @PathVariable long id) throws IOException {

		SportTeam team = teamService.findOneSportTeam(id);
		if (team != null) {
			String filenameLogo = userService.handleUploadImagetoDatabase(fileLogo, team.getId(),
					FilesPath.FILES_TEAMS_LOGO.toString());
			String filenameStadium = userService.handleUploadImagetoDatabase(fileStadium, team.getId(),
					FilesPath.FILES_TEAMS_COVER.toString());

			team.setLogo_image(filenameLogo);
			team.setStadium_image(filenameStadium);
			teamService.saveSportTeam(team);
			return new ResponseEntity<>(team, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/logo/{id}")
	public ResponseEntity<HttpStatus> getLogoImage(@PathVariable long id, HttpServletResponse response)
			throws FileNotFoundException, IOException {

		SportTeam team = teamService.findOneSportTeam(id);
		response.addHeader("Content-type", "image/jpeg");

		if (team != null && team.getLogo_image() != "unknown") {
			File file = userService.handleFileDownload(response, team.getLogo_image(), "logos");
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			// CLasspath access
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(FilesPath.FILES_TEAMS_LOGO + "/" + "unknown-shield.png");
			FileCopyUtils.copy(is, response.getOutputStream());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/stadium/{id}")
	public ResponseEntity<HttpStatus> getStadiumImage(@PathVariable long id, HttpServletResponse response)
			throws FileNotFoundException, IOException {

		SportTeam team = teamService.findOneSportTeam(id);
		response.addHeader("Content-type", "image/jpeg");

		if (team != null && team.getStadium_image() != "unknown") {
			File file = userService.handleFileDownload(response, team.getLogo_image(), "covers");
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(FilesPath.FILES_TEAMS_COVER + "/" + "unknown-stadium.png");
			FileCopyUtils.copy(is, response.getOutputStream());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// actuaizar equipos
	@PutMapping("/sports/{id}")
	public ResponseEntity<SportTeam> updateSportTeam(@PathVariable long id, @RequestBody SportTeam updatedTeam) {

		SportTeam team = teamService.findOneSportTeam(id);

		if (team != null) {
			updatedTeam.setId(team.getId());
			teamService.saveSportTeam(updatedTeam);

			return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/egames/{id}")
	public ResponseEntity<EgamesTeam> updateEgamesTeam(@PathVariable long id, @RequestBody EgamesTeam updatedTeam) {

		EgamesTeam team = teamService.findOneEgamesTeam(id);

		if (team != null) {
			updatedTeam.setId(team.getId());
			teamService.saveEgamesTeam(updatedTeam);

			return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// borrar equipos
	@DeleteMapping("/{id}")
	public ResponseEntity<Team> deleteTeam(@PathVariable long id) {

		Team team = teamService.findOneTeam(id);

		if (team != null) {
			teamService.delete(id);
			return new ResponseEntity<>(team, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
