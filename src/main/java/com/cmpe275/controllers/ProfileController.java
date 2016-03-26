package com.cmpe275.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpe275.exceptions.ResourceNotFoundException;
import com.cmpe275.model.Profile;
import com.cmpe275.model.ProfileDao;

@Controller
public class ProfileController {

	@Autowired
	ProfileDao profileDao;

	// question 5
	@RequestMapping("/profile/delete/{id}")
	public String deleteProfile(@PathVariable(value = "id") String id,
			Model model) {
		try {
			Profile profile = profileDao.findById(id);
			if (profile == null) {
				throw new ResourceNotFoundException(
						"Sorry, the requested user with id" + id
								+ "does not exits!");
			}
			this.profileDao.delete(profile);
			model.addAttribute("profile", new Profile());
			return "redirect:/profile";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Profile not found!");
		}
	}

	// question 1 updte
	@RequestMapping(value = "/profile/update")
	public String UpdateProfile(@ModelAttribute Profile profile, Model model) {
		model.addAttribute("profile", profile);
		try {
			this.profileDao.save(profile);
			model.addAttribute("profile", profile);
			return "form";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Profile not found!");
		}
	}

	// question 1 and 2
	@RequestMapping("/profile/{id}")
	public String getProfile(@PathVariable(value = "id") String id,
			@PathParam(value = "brief") boolean brief, Model model) {
		try {
			Profile profile = profileDao.findById(id);
			if (profile == null) {
				throw new ResourceNotFoundException(
						"Sorry, the requested user with id" + id
								+ "does not exits!");
			}
			model.addAttribute("profile", profile);
			if (brief) {
				return "plain";
			}
			return "form";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Profile not found!");
		}
	}

	// question 3
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("profile", new Profile());
		return "create";
	}

	// question 3
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String createSuccess(@ModelAttribute Profile profile,
			@RequestParam(value = "action", required = true) String action,
			Model model) {
		if (action.equals("delete")) {
			Profile profile1 = profileDao.findById(profile.getId());
			if (profile1 == null) {
				throw new ResourceNotFoundException(
						"Sorry, the requested user with id" + profile.getId()
								+ "does not exits!");
			}
			this.profileDao.delete(profile1);
			model.addAttribute("profile", new Profile());
			return "redirect:/profile";
		}
		if (action.equals("update")) {
			Profile profile1 = profileDao.findById(profile.getId());
			if (profile1 == null) {
				throw new ResourceNotFoundException(
						"Sorry, the requested user with id" + profile.getId()
								+ "does not exits!");
			}
			this.profileDao.save(profile);
			model.addAttribute("profile", profile);
			return "redirect:/profile/"+ profile.getId();
		}
		if (action.equals("create")) {
			model.addAttribute("profile", profile);
			this.profileDao.save(profile);
			return "form";
		}
		return action;
	}
}
