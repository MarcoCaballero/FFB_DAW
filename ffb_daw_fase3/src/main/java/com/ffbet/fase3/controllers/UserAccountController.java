/**
 * 
 */
package com.ffbet.fase3.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

import com.ffbet.fase3.domain.CreditCard;
import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.Team;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.CreditCardRepository;
import com.ffbet.fase3.repositories.MatchRepository;
import com.ffbet.fase3.repositories.SportTeamRepository;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

/**
 * @author Marco
 *
 */
@Controller
public class UserAccountController extends RedirectController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserAuthComponent userComp;
	@Autowired
	CreditCardRepository creditCardRepo;

	@Autowired
	MatchRepository matchRepo;
	@Autowired
	SportTeamRepository teamRepo;

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
			model.addAttribute("user", userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
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
			team = teamRepo.findOne(num);
		}

		model.addAttribute("Teams", teamRepo.findByType("Fútbol"));

		if (team != null) {
			model.addAttribute("SelectedTeam", team);
			model.addAttribute("notFinishedMatches", matchRepo.findByNotFinishedAndTeam("Fútbol", team.getName()));
			model.addAttribute("finishedMatches", matchRepo.findByFinishedAndTeam("Fútbol", team.getName()));
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
			filename = handleUploadImagetoDatabase(imageMultiPartFile, user.getId(),
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
			System.out.println("HOLA CONTRASEÑAS IGUALES");
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

			userRepo.save(user);
		} else {
			showsPasswdError = true;
		}

		return redirectToAccount;
	}

	@RequestMapping("/images/avatars/{fileName}")
	public void handleAvatarsFile(Model model, HttpServletResponse response, @PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = handleFileDownload(model, response, fileName, "avatars", res);

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
		}

	}

	/* PAYMENT SERVICES */

	@GetMapping(value = { "/user-account/addCredit", "/user-account/addCredit/" })
	public String getTemplateCredit(HttpServletRequest request, Model model) {
		if (userComp.isLoggedUser()) {
			showsUserMenu = true;
			model.addAttribute("user", userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
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
			model.addAttribute("user", userRepo.findByEmail(userComp.getLoggedUser().getEmail()));
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

	@PostMapping("/user-account/addCredit/{id}")
	public String updateUserCredit(Model model, @PathVariable("id") long id, @RequestParam("name") String name,
			@RequestParam("type") String type, @RequestParam("cardNumber") String cardNumber,
			@RequestParam("Year") int expirationMonth, @RequestParam("Month") int expirationYear,
			@RequestParam("ccv") int ccv, @RequestParam("amount") String amount) {
		User user = userRepo.findOne(id);
		boolean exists = false;
		CreditCard creditcard = null;
		for (int i = 0; i < user.getCards().size(); i++) {
			if (user.getCards().get(i).getCardNumber().equals(cardNumber)) {
				exists = true;
				creditcard = user.getCards().get(i);
			}
		}
		try {
			if (exists) {
				if (creditcard != null) {
					if (creditcard.equalsData(type, name, cardNumber, expirationMonth, expirationYear, ccv)) {
						long amountSelected = Long.parseLong(amount);
						if (creditcard.sendMoney(amountSelected)) {
							user.addCreditfromCard(amountSelected);
						} else {
							// NOT CREDIT
							showsCardError = true;
							return "redirect:/user-account/addCredit";
						}
						userRepo.save(user);
					} else {
						showsCardError = true;
						return "redirect:/user-account/addCredit";
					}

				}
			} else {

				long amountSelected = Long.parseLong(amount);

				creditcard = new CreditCard(type, name, cardNumber, expirationMonth, expirationYear, ccv);
				user.addCard(creditcard);
				if (creditcard.sendMoney(amountSelected)) {
					user.addCreditfromCard(amountSelected);
				} else {
					// NOT CREDIT
					showsCardError = true;
				}

				userRepo.save(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			showsCardError = true;
			e.printStackTrace();
		}

		return redirectToAccount;

	}

	@RequestMapping("/user-account/selectionTeam/{id}")
	public String selectionTeam(Model model, @PathVariable long id) {

		team = teamRepo.findOne(id);

		// model.addAttribute("SelectedTeam", team);

		return redirectToAccount;
	}

}
