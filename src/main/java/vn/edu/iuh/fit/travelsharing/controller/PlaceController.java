package vn.edu.iuh.fit.travelsharing.controller;

import javax.servlet.http.HttpSession;

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

import vn.edu.iuh.fit.travelsharing.controller.form.PlaceForm;
import vn.edu.iuh.fit.travelsharing.entity.Place;
import vn.edu.iuh.fit.travelsharing.entity.User;
import vn.edu.iuh.fit.travelsharing.service.PlaceService;
import vn.edu.iuh.fit.travelsharing.service.UserService;

@Controller
@RequestMapping(value = "/place")
public class PlaceController {
	final static Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);

	@Autowired
	PlaceService placeService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Place/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(PlaceForm form, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/user/login";
		}
		return "Place/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute PlaceForm form,
			BindingResult result, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "validation error");
			return create(form, model);
		}
		try {
			Place place = placeService.convertToEntity(form);
			
			user.createPlace(place);
			userService.save(user, 1);
			model.addAttribute("place", place);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}

		return "redirect:/user/created";
	}
}
