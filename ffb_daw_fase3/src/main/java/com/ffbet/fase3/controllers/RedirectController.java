/**
 * 
 */
package com.ffbet.fase3.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.ffbet.fase3.domain.EgamesTeam;
import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.SportTeam;

/**
 * Abstract parent class {@link RedirectController} Defines an abstract class
 * that could be used by all controllers child who extends it, and provides
 * variables and methods that could be wholly shared by all subclasses to check
 * the correct URLs without last bar "/page/ --> "/page" and set the resources
 * context at each level (/page, /page/method, /page/method/{id})
 * 
 * @see {@link SportTeam}, {@link EgamesTeam}
 * @author Marco
 * @version 1.0
 */

public abstract class RedirectController {
	
	/**
	 * Method {@linkplain check_url()} to get the correct template from similar
	 * URLs. Considering wrong path = "/page/" or /page/method/", a good path
	 * looks like "/page", "page/method"
	 * 
	 * @param request
	 * @param template_path
	 * @return (String) The correct path if wrong or 'template_path' if the URLs
	 *         is okey.
	 */
	public String check_url(HttpServletRequest request, String template_path) {
		// URL request from browser
		String requestedUri = request.getRequestURI();
		// IsCorrect if the last char of requestedUri isn't a "/"
		boolean isCorrect = !(requestedUri.lastIndexOf("/") == requestedUri.length() - 1);
		// Occurences "/" in the requested URL
		int barOccurrences = StringUtils.countOccurrencesOf(requestedUri, "/");

		if (isCorrect) {
			return template_path;
		} else {
			String redirectTo = requestedUri.substring(0, requestedUri.length() - 1);
			String redirectRoot = "redirect:..";
			for (int i = 2; i < barOccurrences; i++) {
				redirectRoot = redirectRoot.concat("/..");
			}

			return redirectRoot.concat(redirectTo);
		}

	}


	public File handleFileDownload(Model model, HttpServletResponse response, String fileName,
			String fileFolder, HttpServletResponse res) throws FileNotFoundException, IOException {
		String file_folder_absolute;
		switch (fileFolder) {
		case "avatars":
			file_folder_absolute = FilesPath.FILES_AVATARS.toString();
			break;
		case "covers":
			file_folder_absolute = FilesPath.FILES_TEAMS_COVER.toString();
			break;
		case "logos":
			file_folder_absolute = FilesPath.FILES_TEAMS_LOGO.toString();
			break;
		case "promos":
			file_folder_absolute = FilesPath.FILES_PROMOS.toString();
			break;
		default:
			file_folder_absolute = "UnknownFolder";
			break;
		}
		
		if(!file_folder_absolute.equals("UnknownFolder")){
			File file = new File(file_folder_absolute, fileName + ".jpg");
			return file;
		}else{
			res.sendError(404, "File" + fileName + "(" + fileFolder + ") does not exist");
			return null;
		}
		

	}

}
