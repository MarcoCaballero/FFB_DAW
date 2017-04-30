package com.ffbet.fase3.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ffbet.fase3.domain.User;
import com.ffbet.fase3.services.UserService;
import com.ffbet.fase3.storage.StorageFileNotFoundException;
import com.ffbet.fase3.storage.StorageService;

@RestController
@RequestMapping("/api/storage")
public class FileUploadController {

	private final StorageService storageService;
	@Autowired
	private UserService userService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletResponse response)
			throws FileNotFoundException, IOException, StorageFileNotFoundException {

		Resource file = storageService.loadAsResource(filename);
		if (file.exists()) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/png").body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/")
	public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		User user = userService.handleUserLoggedFromComponent();
		if (user != null) {

			if (!file.isEmpty()) {
				String name = user.getName() + "-" + user.getId();
				user.setPhoto_url(name);
				user.setPhotoSelected(true);

				storageService.store(file);
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + file.getOriginalFilename() + "!");

				userService.updateUser(user);

				return ResponseEntity.ok().build();
			}

		}
		return ResponseEntity.noContent().build();
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
