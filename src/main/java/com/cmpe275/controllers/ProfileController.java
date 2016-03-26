package com.cmpe275.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe275.exceptions.ResourceNotFoundException;
import com.cmpe275.model.Profile;
import com.cmpe275.model.ProfileDao;

@Controller
public class ProfileController {

	@Autowired
	ProfileDao profileDao;

	@RequestMapping("/greeting")
	public String hello(
			@RequestParam(value = "firstname", required = false, defaultValue = "World") String firstname,
			Model model) {
		model.addAttribute("firstname", firstname);
		return "hello";
	}
	@RequestMapping("/profile/delete/{id}")
	public String deleteProfile(@PathVariable(value = "id") String id) {
		try {
			Profile profile = profileDao.findById(id);
			if (profile == null) {
				throw new ResourceNotFoundException("Profile not found!");
			}
			this.profileDao.delete(profile);
			return "create";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Profile not found!");
		}
	}
	
	@RequestMapping("/profile/{id}")
	public String getProfile(@PathVariable(value = "id") String id,
			@PathParam(value = "brief") boolean brief, Model model) {
		try {
			Profile profile = profileDao.findById(id);
			if (profile == null) {
				throw new ResourceNotFoundException("Profile not found!");
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

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	// @ResponseBody
	public String create(Model model) {
		return "create";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String id) {
		try {
			Profile profile = new Profile();
			profile.setId(id);
			profileDao.delete(profile);
		} catch (Exception ex) {
			return "Error deleting the profile:" + ex.toString();
		}
		return "Profile succesfully deleted!";
	}
}
