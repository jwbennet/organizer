package com.bbtech.organizer.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.PersonService;

@Controller
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		List<Person> people = personService.getActive();
		model.addObject("people", people);
		model.setViewName("people/index");
		return model;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("person") Person person, ModelAndView model) {
		String message = "";
		if(person.getId() == null) {
			message = "Person created";
		} else {
			message = "Person updated";
		}
		person = personService.savePerson(person);
		model.addObject("id", person.getId());
		model.addObject("success", true);
		model.addObject("message", message);
		model.setViewName("people/update");
		return model;
	}
	
	@RequestMapping(value = "edit/{personId}", method = RequestMethod.GET)
	public ModelAndView editPerson(@PathVariable Long personId, ModelAndView model) {
		Person person = personService.getPerson(personId);
		model.addObject("person", person);
		model.setViewName("people/edit");
		return model;
	}
	
	@RequestMapping(value = "{personId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable Long personId, ModelAndView model) {
		Person person = personService.getPerson(personId);
		model.addObject("person", person);
		model.setViewName("people/detail");
		return model;
	}
	
	@RequestMapping(value = "view/{personId}", method = RequestMethod.GET)
	public ModelAndView display(@PathVariable Long personId, ModelAndView model) {
		Person person = personService.getPerson(personId);
		model.addObject("person", person);
		model.setViewName("people/display");
		return model;
	}
}
