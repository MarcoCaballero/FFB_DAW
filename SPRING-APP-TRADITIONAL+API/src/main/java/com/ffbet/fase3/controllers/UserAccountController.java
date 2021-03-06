/**
 *
 */
package com.ffbet.fase3.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.ffbet.fase3.domain.BetTicket;
import com.ffbet.fase3.domain.CreditCard;
import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.BetTicketService;
import com.ffbet.fase3.services.CreditCardService;
import com.ffbet.fase3.services.MatchService;
import com.ffbet.fase3.services.TeamService;
import com.ffbet.fase3.services.UserService;

/**
 * @author Marco
 *
 */
@Controller
public class UserAccountController extends RedirectController {

	@Autowired
	private UserService userService;
	@Autowired
	private BetTicketService betTicketService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private CreditCardService cardService;

	@Autowired
	UserAuthComponent userComp;

	String photoA = "";
	String redirectToAccount = "redirect:/user-account/";

	private boolean showsUserMenu = false;
	private boolean showsPhotoError = false;
	private boolean showsPasswdError = false;
	private boolean showsCardError = false;
	// private boolean isPhotoSelected = false;
	private Team team = null;

	@GetMapping(value = { "/user-account", "/user-account/" })
	public String getTemplate(HttpServletRequest request, Model model) {
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;

			User updateduser = userService.findByEmail(userComp.getLoggedUser().getEmail());
			List<BetTicket> listBetAll = betTicketService.findByFinished();
			List<BetTicket> listBetOwnerFinished = new ArrayList<>();
			List<BetTicket> listBetOwnerNotFinished = new ArrayList<>();
			double amountInBet = 0;
			double winAmountInBet = 0;
			for (BetTicket bt : updateduser.getBet_tickets()) {
				amountInBet += bt.getAmount();
				winAmountInBet += bt.getPotentialGain();
				if (listBetAll.contains(bt)) {
					listBetOwnerFinished.add(bt);
				} else {
					listBetOwnerNotFinished.add(bt);
				}
			}

			model.addAttribute("listBetOwnerFinished", listBetOwnerFinished);
			model.addAttribute("listBetOwnerNotFinished", listBetOwnerNotFinished);
			model.addAttribute("amountInBet", amountInBet);
			model.addAttribute("winAmountInBet", winAmountInBet);
			model.addAttribute("user", updateduser);
		} else {
			showsUserMenu = false;
			return "redirect:/logOut";
		}

		model.addAttribute("isUsermenuActive", showsUserMenu);
		model.addAttribute("showsPhotoError", showsPhotoError);
		model.addAttribute("showsPasswdError", showsPasswdError);

		// model.addAttribute("notFinishedMatches",
		// matchRepo.findByNotFinished("Fútbol"));
		// model.addAttribute("finishedMatches",
		// matchRepo.findByFinished("Fútbol"));

		if (team == null) {
			final long num = 1;
			team = teamService.findOneTeam(num);
		}

		model.addAttribute("Teams", teamService.findByTypeSports("Fútbol"));

		if (team != null) {
			model.addAttribute("SelectedTeam", team);
			model.addAttribute("notFinishedMatches", matchService.findByNotFinishedAndTeam("Fútbol", team.getName()));
			model.addAttribute("finishedMatches", matchService.findByFinishedAndTeam("Fútbol", team.getName()));
		}

		// model.addAttribute("isPhotoSelected", isPhotoSelected);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_USER_ACCOUNT.toString());
		return response;

	}

	@PostMapping("/user-account/updateData")
	public String updateUserData(Model model, @RequestParam("namePre") String namePre,
			@RequestParam("surnamePre") String surnamePre, @RequestParam("cityPre") String cityPre,
			@RequestParam("countryPre") String countryPre, @RequestParam("telf") String telephone,
			@RequestParam("dniPre") String dniPre, @RequestParam("locationPre") String locationPre,
			@RequestParam("emailPre") String emailPre, @RequestParam("pass") String pass,
			@RequestParam("passRepeat") String passRepeat, @RequestParam("secondSurname") String secondSurname,
			@RequestParam("fileImage") MultipartFile imageMultiPartFile) throws IOException {
		String filename;
		User user = userComp.getLoggedUser();
		if (!imageMultiPartFile.isEmpty()) {
			filename = userService.handleUploadImagetoDatabase(imageMultiPartFile, user.getId(),
					FilesPath.FILES_AVATARS.toString());
			if (filename.equals("ERROR")) {
				showsPhotoError = true;
			} else {
				showsPhotoError = false;
				user.setPhoto_url(filename);
			}
		} else {
			showsPhotoError = true;
		}

		if (pass.equals(passRepeat)) {
			// System.out.println("HOLA CONTRASEÑAS IGUALES");
			showsPasswdError = false;
			user.setPassword(pass);
			user.setPhotoSelected(!showsPhotoError);

			if (!namePre.isEmpty())
				user.setName(namePre);
			if (!surnamePre.isEmpty())
				user.setSurname(surnamePre);
			if (!countryPre.isEmpty())
				user.setCountry(countryPre);
			if (!locationPre.isEmpty())
				user.setLocation(locationPre);
			if (!dniPre.isEmpty())
				user.setDni(dniPre);
			if (!secondSurname.isEmpty())
				user.setSecondSurname(secondSurname);
			if (!telephone.isEmpty())
				user.setTelephone(telephone);
			if (!emailPre.isEmpty())
				user.setEmail(emailPre);
			if (!cityPre.isEmpty())
				user.setCity(cityPre);

			userService.updateUser(user);
		} else {
			showsPasswdError = true;
		}

		return redirectToAccount;
	}

	@RequestMapping("/images/avatars/{fileName}")
	public void handleAvatarsFile(Model model, HttpServletResponse response, @PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		InputStream file = userService.handleFileDownload(response, fileName, "avatars");

		if (file != null) {
			res.setContentType("image/jpeg");

			FileCopyUtils.copy(file, response.getOutputStream());

		} else {
			res.sendError(404, "File" + fileName + "does not exist");
		}

	}

	/* PAYMENT SERVICES */

	@GetMapping(value = { "/user-account/addCredit", "/user-account/addCredit/" })
	public String getTemplateCredit(HttpServletRequest request, Model model) {
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
			if (!userComp.getLoggedUser().isPhotoSelected()) {
				model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			} else {
				// Use image controller
			}
		} else {
			showsUserMenu = false;
			return "redirect:/logOut";
		}

		model.addAttribute("showsCardError", showsCardError);
		model.addAttribute("isUsermenuActive", showsUserMenu);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_ADD_CREDIT.toString());
		return response;

	}

	@GetMapping(value = { "/user-account/withdrawCredit", "/user-account/withdrawCredit/" })
	public String getTemplateWithdrawCredit(HttpServletRequest request, Model model) {
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
			if (!userComp.getLoggedUser().isPhotoSelected()) {
				model.addAttribute("isMen", userComp.getLoggedUser().isMen());
			} else {
				// Use image controller
			}
		} else {
			showsUserMenu = false;
			return "redirect:/logOut";
		}

		model.addAttribute("isUsermenuActive", showsUserMenu);
		// Checks the URLs with "/*" pattern
		// Delete the last bar if the requested URL is like "/*/"
		String response = check_url(request, TemplatesPath.USER_GET_CREDIT.toString());
		return response;

	}

	@PostMapping("/user-account/addCredit")
	public String updateUserCredit(@RequestParam("name") String name, @RequestParam("type") String type,
			@RequestParam("cardNumber") String cardNumber, @RequestParam("Year") int expirationMonth,
			@RequestParam("Month") int expirationYear, @RequestParam("ccv") int ccv,
			@RequestParam("amount") String amount) {

		CreditCard credit = new CreditCard(type, name, cardNumber, expirationMonth, expirationYear, ccv);

		if (cardService.saveCreditCard(credit, amount) == null) {
			showsCardError = true;
		}

		if (showsCardError) {
			return "redirect:/user-account/addCredit";
		}

		return redirectToAccount;

	}

	@PostMapping("/user-account/userDevolveCredit")
	public String devolveFromFFB(Model model, @RequestParam("amountTo") String amount,
			@RequestParam("cardNumber") String cardNumber) {

		cardService.takeCredit(cardService.getCard(cardNumber), amount);

		return redirectToAccount;

	}

	@RequestMapping("/user-account/selectionTeam/{id}")
	public String selectionTeam(Model model, @PathVariable long id) {

		team = teamService.findOneTeam(id);

		// model.addAttribute("SelectedTeam", team);

		return redirectToAccount;
	}

	@GetMapping("/user-account/checkBet/{id}")
	public String checkBets(Model model, @PathVariable long id) {

		betTicketService.validateBet(id);
		return redirectToAccount;

	}

	@GetMapping(value = { "/user-account/removeBetMatch/{id}", "/user-sportsBet/removeBetMatch/{id}/" })
	public String sendSportBet(HttpServletRequest request, Model model, @PathVariable long id) {
		if (!betTicketService.isBetCheckedYet(id)) {
			betTicketService.removeBetTicketFromUser(id);
		}

		return redirectToAccount;

	}

}
