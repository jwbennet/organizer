package com.bbtech.organizer.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.PersonService;

@Controller
@RequestMapping("/find")
public class ShortcutController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/p/{username}", method = RequestMethod.GET)
	@Transactional
	public String update(@PathVariable String username) {
		Person person = personService.getPersonByUsername(username);
		if(person == null) {
			return "redirect:/people";
		} else {
			return "redirect:/people/" + person.getId();
		}
	}
}
