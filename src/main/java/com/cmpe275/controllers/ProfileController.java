package com.cmpe275.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe275.model.Profile;
import com.cmpe275.model.ProfileDao;

@Controller
public class ProfileController {

	@Autowired
	ProfileDao profileDao;

	@RequestMapping("/greeting")
	public String hello(@RequestParam(value = "firstname", required = false, defaultValue = "World") String firstname,
			Model model) {
		model.addAttribute("firstname", firstname);
		return "hello";
	}
	
	@RequestMapping("/profile/{id}")
	public String getProfile(@PathVariable(value = "id") String id,
			Model model) {
		try {
			Profile profile = profileDao.findById(id);
//			model.addAttribute("firstname", profile.getFirstname());
//			model.addAttribute("lastname", profile.getLastname());
//			model.addAttribute("email", profile.getEmail());
//			model.addAttribute("address", profile.getAddress());
//			model.addAttribute("organization", profile.getOrganization());
//			model.addAttribute("aboutMyself", profile.getAboutMyself());
			model.addAttribute("profile", profile);
			return "hello";
		}catch (Exception e) {
			return "Error creating the profile";
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	//@ResponseBody
	public String create(@ModelAttribute("profileForm")String id, String firstname, String lastname,Model model) {
		try {
			Profile profile = new Profile();
			profile.setId(id);
			profile.setFirstname(firstname);
			profile.setLastname(lastname);
			profileDao.save(profile);
			id = String.valueOf(profile.getId());
			model.addAttribute("id", profile.getId());
			model.addAttribute("firstname", profile.getFirstname());
			model.addAttribute("lastname", profile.getLastname());
			return "index";
		} catch (Exception e) {
			return "Error creating the profile";
		}
	}
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	//@ResponseBody
	public String createpage(Model model) {
		Profile profileForm = new Profile();
		model.addAttribute("profileForm", profileForm);
		return "profile";
	}
	 @RequestMapping("/delete")
	  @ResponseBody
	  public String delete(String id) {
	    try {
	      Profile profile = new Profile();
	      profile.setId(id);
	      profileDao.delete(profile);
	    }
	    catch (Exception ex) {
	      return "Error deleting the profile:" + ex.toString();
	    }
	    return "Profile succesfully deleted!";
	  }
}
