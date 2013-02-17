package com.bbtech.organizer.server.controllers;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Name;
import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.NameService;
import com.bbtech.organizer.server.services.PersonService;

@Controller
@RequestMapping("/names")
public class NameController {

	@Autowired
	private NameService nameService;
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute("name") Name name, ModelAndView model, ServletRequest request) {
		String personId = request.getParameter("person.id");
		String message = "";
		boolean success = false;
		if(StringUtils.isEmpty(personId)) {
			message = "Missing person ID";
		} else {
			Person person = personService.getPerson(Long.valueOf(personId));
			name.setPerson(person);
			if(name.getId() == null) {
				message = "Name created";
			} else {
				message = "Name updated";
			}
			success = true;
			if(name.isPrimary()) {
				nameService.setPrimary(name);
			}
			name = nameService.saveName(name);

		}
		model.addObject("id", name.getId());
		model.addObject("success", success);
		model.addObject("message", message);
		model.setViewName("names/form-response");
		return model;
	}
	
	@RequestMapping(value = "{nameId}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long nameId, ModelAndView model) {
		Name name = nameService.getName(nameId);
		model.addObject("name", name);
		model.setViewName("names/display");
		return model;
	}
	
	@RequestMapping(value = "{nameId}", method = RequestMethod.DELETE)
	@Transactional
	public ModelAndView delete(@PathVariable Long nameId, ModelAndView model) {
		nameService.deleteNameById(nameId);
		model.addObject("id", nameId);
		model.addObject("success", true);
		model.addObject("message", "Name deleted");
		model.setViewName("names/form-response");
		return model;
	}
}
