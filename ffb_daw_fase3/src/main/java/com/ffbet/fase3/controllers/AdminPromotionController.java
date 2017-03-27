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

import com.ffbet.fase3.domain.FilesPath;
import com.ffbet.fase3.domain.Promotion;
import com.ffbet.fase3.domain.TemplatesPath;
import com.ffbet.fase3.security.UserAuthComponent;
import com.ffbet.fase3.services.PromoService;
import com.ffbet.fase3.services.UserService;

@Controller
public class AdminPromotionController extends RedirectController {

	private String template = TemplatesPath.ADMIN_PROMOTION.toString();
	private String redirect = "redirect:/admin-promotions/";

//	@Autowired
//	private PromotionRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private PromoService promoService;

	private boolean goodPromo = false;
	private boolean photoError = false;
	@Autowired
	UserAuthComponent userComp;

	/**
	 * Method {@linkplain getPromotionsTemplate()} uses the abstract class
	 * {@link RedirectController} to get the correct template from similar URLs,
	 * and shows the PROMOTIONS template through the browser.
	 * 
	 * @param request
	 * @param model
	 * @return response
	 */
	@GetMapping(value = { "/admin-promotions", "/admin-promotions/" })
	public String getPromotionsTemplate(HttpServletRequest request, Model model) {

		
		if(userComp.isLoggedUser()){
			model.addAttribute("user", userService.findByEmail(userComp.getLoggedUser().getEmail()));
		}else{
			return "redirect:/logOut";
		}
		
		model.addAttribute("promotions", promoService.findAll());

		model.addAttribute("isPromo", goodPromo);
		model.addAttribute("showsError", photoError);

		String response = check_url(request, template);
		return response;

	}

	/**
	 * Method {@linkplain addPromotion()} add a new promotion to the repository,
	 * and returns the template properties.
	 * 
	 * 
	 * @param request
	 * @param model
	 * @param type
	 * @param title
	 * @param description
	 * @param promotionCode
	 * @return String
	 * @throws IOException
	 */
	@PostMapping(value = { "/admin-promotions/new" })
	public String addPromotion(Promotion newPromotion, @RequestParam String type,
			@RequestParam("promoCode") String promotionCode, @RequestParam("promoTitle") String title,
			@RequestParam("promoDescription") String description, @RequestParam("promoImage") MultipartFile image,
			@RequestParam("promoQuantity" ) String quantity)
			throws IOException {

		String filePromo;

		if (!title.isEmpty() && !description.isEmpty() && !promotionCode.isEmpty() && !quantity.isEmpty()) {
			newPromotion.setType(type);
			newPromotion.setTitle(title);
			newPromotion.setDescription(description);
			newPromotion.setPromotionCode(promotionCode);
			newPromotion.setQuantity(Integer.parseInt(quantity));

			promoService.save(newPromotion);

			promoService.findByTitle(title);

			if (!image.isEmpty()) {
				filePromo = handleUploadImagetoDatabase(image, newPromotion.getId(), FilesPath.FILES_PROMOS.toString());
				if (filePromo.equals("ERROR")) {
					photoError = true;
				} else {
					photoError = false;
					newPromotion.setPromotionImage(filePromo);
				}
			}

			promoService.save(newPromotion);

			goodPromo = false;
		} else {
			goodPromo = true;
		}

		return redirect;
	}

	@RequestMapping("/images/promos/{fileName}")
	public void handleAvatarsFileLogo(Model model, HttpServletResponse response, @PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = handleFileDownload(model, response, fileName, "promos", res);

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
		}

	}

	/**
	 * Method {@linkplain deletePromotionByID()} delete a specific promotion
	 * from the repository, and returns the template properties.
	 * 
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = { "/admin-promotions/delete/{id}" })
	public String deletePromotionByID(@PathVariable long id) {

		promoService.delete(id);
		return redirect;

	}

	// /** API REST
	// * Method {@linkplain getPromotionByID()} get the correct template from
	// similar URLs,
	// * and shows it through the browser with the match [selected by id]
	// * properties.
	// *
	// *
	// * @param request
	// * @param model
	// * @param id
	// * @return ResponseEntity<Promotion>
	// */
	// @GetMapping(value = { "/admin-promotions/{id}", "/admin-promotions/{id}/"
	// })
	// public ResponseEntity<Promotion> getPromotionByID(HttpServletRequest
	// request, @PathVariable long id) {
	//
	//
	// Promotion promotion = repository.findOne(id);
	//
	// if(promotion != null){
	// return new ResponseEntity<>(promotion, HttpStatus.OK);
	// }else{
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	//
	// }

	// /** API REST
	// * Method {@linkplain deletePromotionByID()} delete the correct promotion
	// from repository,
	// * and returns the promotion selected [selected by id]
	// * properties.
	// *
	// *
	// * @param request
	// * @param model
	// * @param id
	// * @return ResponseEntity<Promotion>
	// */
	// @RequestMapping(value = { "/admin-promotions/{id}",
	// "/admin-promotions/{id}/" }, method = RequestMethod.DELETE)
	// public ResponseEntity<Promotion> deletePromotionByID(HttpServletRequest
	// request, Model model, @PathVariable long id) {
	//
	//
	// Promotion promotion =repository.findOne(id);
	// repository.delete(id);
	//
	// if(promotion != null){
	// return new ResponseEntity<>(promotion, HttpStatus.OK);
	// }else{
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	//
	// }

	// /** API REST
	// * Method {@linkplain updatePromotionByID()} update the correct promotion
	// from repository,
	// * and returns the promotion selected [selected by id]
	// * properties.
	// *
	// *
	// * @param request
	// * @param model
	// * @param id
	// * @return ResponseEntity<Promotion>
	// */
	// @RequestMapping(value = { "/admin-promotions/update/{id}",
	// "/admin-promotions/update/{id}/" }, method = RequestMethod.PUT)
	// public ResponseEntity<Promotion> updatePromotionByID(HttpServletRequest
	// request, Model model, @PathVariable long id, @RequestBody Promotion
	// updatePromotion) {
	//
	//
	// Promotion promotion =repository.findOne(id);
	//
	// if(promotion != null){
	// updatePromotion.setId(id);
	// repository.save(updatePromotion);
	// return new ResponseEntity<>(promotion, HttpStatus.OK);
	// }else{
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	//
	// }

	// /** API REST
	// * Method {@linkplain deletePromotionByID()} add a new promotion to the
	// repository,
	// * and returns the promotion created [selected by id]
	// * properties.
	// *
	// *
	// * @param request
	// * @param model
	// * @param id
	// * @return ResponseEntity<Promotion>
	// */
	// @RequestMapping(value = { "/admin-promotions/new",
	// "/admin-promotions/new/" }, method = RequestMethod.POST)
	// @ResponseStatus(HttpStatus.CREATED)
	// public Promotion addPromotion(HttpServletRequest request, @RequestBody
	// Promotion newPromotion) {
	//
	// repository.save(newPromotion);
	//
	// return newPromotion;
	// }

	// /** API REST
	// * Method {@linkplain getPromotions()} get all existing promotions from
	// the repository,
	// * and returns a collections of them.
	// * properties.
	// *
	// *
	// * @param request
	// * @param model
	// * @param id
	// * @return Collection<Promotion>
	// */
	// @RequestMapping(value = { "/admin-promotions/promotions",
	// "/admin-promotions/promotions/" }, method= RequestMethod.GET)
	// public Collection<Promotion> getPromotions() {
	// System.out.println("as");
	// return repository.findAll();
	// }
}
