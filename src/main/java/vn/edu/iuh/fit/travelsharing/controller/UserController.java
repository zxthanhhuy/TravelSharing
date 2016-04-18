package vn.edu.iuh.fit.travelsharing.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.iuh.fit.travelsharing.controller.form.LoginForm;
import vn.edu.iuh.fit.travelsharing.controller.form.UserForm;
import vn.edu.iuh.fit.travelsharing.entity.User;
import vn.edu.iuh.fit.travelsharing.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends TravelSharingController {
	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	final static int PAGE_SIZE = 20;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ServletContext servletContext;
	
	@SuppressWarnings("serial")
	final static Map<String, String> HEADER_INDEX = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("title", "Product");
					put("subtitle", "list");
				}
			});

	@SuppressWarnings("serial")
	final static Map<String, String> HEADER_DETAIL = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("title", "User");
					put("subtitle", "detail");
				}
			});

	@SuppressWarnings("serial")
	final static Map<String, String> HEADER_CREATE = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("title", "User");
					put("subtitle", "create");
				}
			});

	@SuppressWarnings("serial")
	final static Map<String, String> HEADER_EDIT = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("title", "User");
					put("subtitle", "edit");
				}
			});

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public String index(Model model) {
	// return index(null, model);
	// }

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/user/login";
		}
		return "User/index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(UserForm form, Model model) {
		// addHeaderAttr(HEADER_CREATE, model);
		return "User/register";
	}

	private void saveImage(String filename, MultipartFile image)
			throws RuntimeException, IOException {
		try {
			File file = new File(servletContext.getRealPath("/images/uploaded") + "/"
					+ filename);

			FileUtils.writeByteArrayToFile(file, image.getBytes());
			System.out
					.println("Go to the location:  "
							+ file.toString()
							+ " on your computer and verify that the image has been stored.");
		} catch (IOException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			@Validated @ModelAttribute UserForm form,
			BindingResult result,
			@RequestParam(value = "uploadImage", required = false) MultipartFile image,
			Model model) throws RuntimeException {		
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "validation error");
			return register(form, model);
		}
		if (image.isEmpty()) {
			model.addAttribute("errorMessage", "validation error");
			return register(form, model);
		}
		try {
			saveImage(form.getName() + ".jpg", image);
		} catch (IOException e) {
			result.reject(e.getMessage());
			return register(form, model);
		}
		form.setProfileImage(form.getName() + ".jpg");
		User user = userService.save(form, 1);
		model.addAttribute("user", user);
		return "User/detail";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(LoginForm form, Model model) {
		if (session.getAttribute("user") != null) {
			return "redirect:/user/index";
		}
		return "User/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute LoginForm form,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "validation error");
			return login(form, model);
		}
		String email = form.getEmail();
		String password = form.getPassword();
		Long userNodeId = userService.getUserNodeIdByEmailAndPassword(email,
				password);
		if (userNodeId == null) {
			model.addAttribute("errorMessage", "validation error");
			return login(form, model);
		}
		User user = userService.findOne(userNodeId);
		if (user == null) {
			model.addAttribute("errorMessage", "validation error");
			return login(form, model);
		}
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(30 * 2 * 10);

		return "redirect:/user/index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(LoginForm form) {
		session.removeAttribute("user");
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/rated", method = RequestMethod.GET)
	public String findRatedPlaces() {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		return "User/rated";
	}
	
	@RequestMapping(value = "/traveled", method = RequestMethod.GET)
	public String findTraveledPlaces() {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		return "User/traveled";
	}

	@RequestMapping(value = "/followed", method = RequestMethod.GET)
	public String findFollowedUsers() {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		return "User/followed";
	}
	
	@RequestMapping(value = "/created", method = RequestMethod.GET)
	public String findCreatedUsers() {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		return "User/created";
	}
}
