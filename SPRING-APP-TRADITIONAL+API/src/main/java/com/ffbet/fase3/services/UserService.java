package com.ffbet.fase3.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.repositories.UserRepository;
import com.ffbet.fase3.security.UserAuthComponent;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserAuthComponent userComp;

	/* USER REPOSITORY METHODS */

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findOne(long id) {
		return userRepo.findOne(id);
	}

	public User findByName(String name) {
		return userRepo.findByName(name);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public void save(User user) {
		if (userComp.isLoggedUser()) {
			if (userComp.getLoggedUser().getRoles().contains("ROLE_ADMIN"))
				user.setRoles("ROLE_ADMIN");
		} else {
			user.setRoles("ROLE_USER");
		}

		user.setCredit(0.0);
		userRepo.save(user);

	}
	
	public void updateUser(User user){
		userRepo.save(user);
	}

	public void delete(long id) {
		userRepo.delete(id);
	}

	/* USER LOGGED METHODS */

	public User handleUserLoggedFromComponent() {

		User userLogged = null;
		if (userComp.isLoggedUser()) {
			userLogged = findByEmail(userComp.getLoggedUser().getEmail());
			updateUser(userLogged);
		}
		return userLogged;
	}
	
	/* SUBIDA DE IMÁGENES */
	public String handleUploadImagetoDatabase(MultipartFile imageMultiPartFile, long idPath, String files_folder)
			throws IOException {
		String id = String.valueOf(idPath);
		String filename;

		if (files_folder.equals(FilesPath.FILES_AVATARS.toString())) {
			filename = "photo_avatar" + id + ".jpg";
		} else if (files_folder.equals(FilesPath.FILES_TEAMS_COVER.toString())) {
			filename = "photo_team_cover" + id + ".jpg";
		} else if (files_folder.equals(FilesPath.FILES_TEAMS_LOGO.toString())) {
			filename = "photo_team_logo" + id + ".jpg";
		} else if (files_folder.equals(FilesPath.FILES_PROMOS.toString())) {
			filename = "photo_promo" + id + ".jpg";
		} else {
			filename = "unknownfoldersource" + id + ".jpg";
		}

		if (!imageMultiPartFile.isEmpty()) {
			try {
				File filesFolder = new File(files_folder);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), filename);
				System.out.println("Absoulte : " + filesFolder.getAbsolutePath());
				imageMultiPartFile.transferTo(uploadedFile);
				return filename;
			} catch (Exception e) {
				return "ERROR";
			}

		}
		return "ERROR";

	}

}
