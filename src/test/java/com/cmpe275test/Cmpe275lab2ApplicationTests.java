package com.cmpe275test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.cmpe275.Cmpe275lab2Application;
import com.cmpe275.controllers.ProfileController;
import com.cmpe275.model.Profile;
import com.cmpe275.model.ProfileDao;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Cmpe275lab2Application.class)
@WebAppConfiguration
public class Cmpe275lab2ApplicationTests {
	
    @Test
    public void testGetProfileBrief() {
    	
    	ProfileDao personService = mock(ProfileDao.class);
    	
        when(personService.findById("1")).thenReturn(new Profile("1", "Chen"));
 
        ProfileController controller = new ProfileController();
        controller.setProfileDao(personService);
 
        Model model = new ExtendedModelMap();
 
        String view = controller.getProfile("1", true, model);
 
        assertEquals("View name", "plain", view);
 
        Profile actualPerson = (Profile) model.asMap().get("profile");
 
        assertEquals("matching ID", "1", actualPerson.getId());
        assertEquals("matching Name", "Chen", actualPerson.getFirstname());
    }
    
    @Test
    public void testGetProfile() {
    	
    	ProfileDao personService = mock(ProfileDao.class);
    	
        when(personService.findById("1")).thenReturn(new Profile("1", "Chen"));
 
        ProfileController controller = new ProfileController();
        controller.setProfileDao(personService);
 
        Model model = new ExtendedModelMap();
 
        String view = controller.getProfile("1", false, model);
 
        assertEquals("View name", "form", view);
 
        Profile actualPerson = (Profile) model.asMap().get("profile");
 
        assertEquals("matching ID", "1", actualPerson.getId());
        assertEquals("matching Name", "Chen", actualPerson.getFirstname());
    }
}
