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
	
	public ProfileDao getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	
	/**
	 *  Implement the method to find user profile.
	 *  1. If userId is available, retrieve user's profile.
	 *  2. If the profile of the given user ID does not exist, a customized 404 HTML page with the message “Sorry, the requested user with ID XXX does not exist.” Note: XXX is the ID specified in the request.
	 *  3. If brief=true This returns an HTML that encapsulates the given user’s full profile in plain text format
	 *  @param id
	 *  @return String
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public String getProfile(@PathVariable(value = "id") String id,
			@PathParam(value = "brief") boolean brief, Model model) {

		Profile profile = profileDao.findById(id);
		if (profile == null) {
			throw new ResourceNotFoundException(
					"Sorry, the requested user with id" + id
							+ " does not exits!");
		}
		model.addAttribute("profile", profile);
		if (brief) {
			return "plain";
		}
		return "form";

	}

	/**
	 *  Implement the method to create or update user profile.
	 *  @param id
	 *  @return String
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	public String createOrUpdateProfile(@PathVariable(value = "id") String id,
			@PathParam(value = "firstname") String firstname,
			@PathParam(value = "lastname") String lastname,
			@PathParam(value = "email") String email,
			@PathParam(value = "address") String address,
			@PathParam(value = "organization") String organization,
			@PathParam(value = "aboutmyself") String aboutmyself, Model model) {
		try {
			Profile profile = profileDao.findById(id);
			if (profile == null) {
				profile = new Profile();
				profile.setId(id);
			}
			profile.setFirstname(firstname);
			profile.setLastname(lastname);
			profile.setEmail(email);
			profile.setAddress(address);
			profile.setOrganization(organization);
			profile.setAboutMyself(aboutmyself);
			model.addAttribute("profile", profile);
			this.profileDao.save(profile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/profile/{id}?brief=false";
	}

	/**
	 *  Implement the method to delete user profile for the given userID.
	 *  1. If the profile does not exist, it should return the same 404 page
	 *  2. If the profile exists, delete the corresponding profile, and redirect the request to the profile creation page
	 *  @param id
	 *  @return String
	 */
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
	public String deleteProfile(@PathVariable(value = "id") String id,
			Model model) {

		Profile profile = profileDao.findById(id);
		if (profile == null) {
			throw new ResourceNotFoundException(
					"Sorry, the requested user with id" + id
							+ "does not exits!");
		}
		this.profileDao.delete(profile);
		model.addAttribute("profile", new Profile());
		// return "redirect:/profile";
		return "create";
	}

	/**
	 *  Implement the method to create user profile.
	 *  @param Model
	 *  @return String
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("profile", new Profile());
		return "create";
	}

	/**
	 *  Implement the method to create user profile.
	 *  All fields including the ID will be created.
	 *  @param Profile
	 *  @return String
	 */
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
			return "redirect:/profile/" + profile.getId();
		}
		if (action.equals("create")) {
			model.addAttribute("profile", profile);
			this.profileDao.save(profile);
			return "form";
		}
		return action;
	}
}
